package com.xpit.vblog.controller;

import com.xpit.vblog.common.vo.RespVO;
import com.xpit.vblog.entity.Category;
import com.xpit.vblog.service.impl.CategoryServiceImpl;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 超级管理员才可以操作分类接口
 */
@RestController
@Api(tags = "分类接口")
@RequestMapping("/admin/category")
public class CategoryController {
    @Autowired
    private CategoryServiceImpl categoryService;


    @GetMapping("/all")
    @ApiOperation("获取所有分类")
    public List<Category> getAllCategories() {
        return categoryService.getAllCategories();
    }

    @DeleteMapping("/del/{ids}")
    @ApiOperation("删除分类")
    public RespVO delCategoryById(@PathVariable String ids) {
        boolean result = categoryService.deleteCategoryByIds(ids);
        if (result) {
            return new RespVO("success", "删除成功");
        } else {
            return new RespVO("error", "删除失败");
        }
    }

    @PostMapping("/add")
    @ApiOperation("新增分类")
    public RespVO addNewCate(Category category) {
        if (category.getCateName() == null || "".equals(category.getCateName())) {
            return new RespVO("error", "请输入分类名称");
        }
        int result = categoryService.addCategory(category);
        if (result == 1) {
            return new RespVO("success", "新增栏目成功");
        }
        return new RespVO("error", "新增失败");
    }


    @PutMapping("/update")
    @ApiOperation("更新分类")
    public RespVO updateCate(Category category) {
        if (category.getCateName() == null || "".equals(category.getCateName())) {
            return new RespVO("error", "分类名称不能为空");
        }
        int result = categoryService.updateCategoryById(category);
        if (result == 1) {
            return new RespVO("success", "更新成功");
        }
        return new RespVO("error", "更新失败");
    }


}
