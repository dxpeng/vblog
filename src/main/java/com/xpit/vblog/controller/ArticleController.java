package com.xpit.vblog.controller;

import com.xpit.vblog.common.utils.Util;
import com.xpit.vblog.common.vo.RespVO;
import com.xpit.vblog.entity.Article;
import com.xpit.vblog.service.impl.ArticleServiceImpl;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;

@RestController
@Api(tags = "文章接口")
@RequestMapping("/article")
public class ArticleController {
    private SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
    @Autowired
    private ArticleServiceImpl articleService;

    @PostMapping("/add")
    @ApiOperation("新增文章")
    public RespVO addNewArticle(Article article) {
        int result = articleService.addNewArticle(article);
        if (result == 1) {
            return new RespVO("success", article.getId().toString());
        } else {
            return new RespVO("error", article.getState() == 0 ? "文章保存失败" : "文章发布失败");
        }
    }

    /**
     * 上传图片
     *
     * @return 返回值为图片地址
     */
    @PostMapping("/uploadimg")
    @ApiOperation("上传图片")
    public RespVO uploadimg(HttpServletRequest req, MultipartFile img) {
        StringBuffer url = new StringBuffer();
        String filePath = "/blogimg/" + sdf.format(new Date());
        String imgFolderPath = req.getServletContext().getRealPath(filePath);
        File imgFolder = new File(imgFolderPath);
        if (!imgFolder.exists()) {
            imgFolder.mkdirs();
        }
        url.append(req.getScheme()).append("://")
                .append(req.getServerName())
                .append(":")
                .append(req.getServerPort())
                .append(req.getContextPath())
                .append(filePath);
        String imgName = UUID.randomUUID() + "_" + img.getOriginalFilename().replaceAll(" ", "");
        try {
            IOUtils.write(img.getBytes(), new FileOutputStream(new File(imgFolder, imgName)));
            url.append("/").append(imgName);
            return new RespVO("success", url.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new RespVO("error", "上传失败");
    }

    @GetMapping("/all")
    @ApiOperation("获取文章列表")
    public Map<String, Object> getArticleByState(@RequestParam(value = "state", defaultValue = "-1") Integer state,
                                                 @RequestParam(value = "page", defaultValue = "1") Integer page,
                                                 @RequestParam(value = "count", defaultValue = "10") Integer count,
                                                 String keywords) {
        int totalCount = articleService.getArticleCountByState(state, Util.getCurrentUser().getId(), keywords);
        List<Article> articleList = articleService.getArticleByState(state, page, count, keywords);
        Map<String, Object> map = new HashMap<>();
        map.put("totalCount", totalCount);
        map.put("articleList", articleList);
        return map;
    }

    @GetMapping("/{aid}")
    @ApiOperation("获取文章详情")
    public Article getArticleById(@PathVariable Long aid) {
        return articleService.getArticleById(aid);
    }

    @PutMapping("/update/state")
    @ApiOperation("更新文章状态")
    public RespVO updateArticleState(Long[] aids, Integer state) {
        if (articleService.updateArticleState(aids, state) == aids.length) {
            return new RespVO("success", "操作成功");
        }
        return new RespVO("error", "操作失败");
    }

    @PutMapping("/restore")
    @ApiOperation("从垃圾箱还原文章")
    public RespVO restoreArticle(Integer articleId) {
        if (articleService.restoreArticle(articleId) == 1) {
            return new RespVO("success", "还原成功");
        }
        return new RespVO("error", "还原失败");
    }

    @GetMapping("/dataStatistics")
    @ApiOperation("获取数据统计")
    public Map<String, Object> getDataStatistics() {
        Map<String, Object> map = new HashMap<>();
        List<String> categories = articleService.getCategories();
        List<Integer> dataStatistics = articleService.getDataStatistics();
        map.put("categories", categories);
        map.put("ds", dataStatistics);
        return map;
    }

}
