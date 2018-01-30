package me.wuwenbin.blog.v201801.web.management;

import me.wuwenbin.blog.v201801.model.bo.CommentQueryBo;
import me.wuwenbin.blog.v201801.model.vo.CommentVo;
import me.wuwenbin.blog.v201801.repository.CommentRepository;
import me.wuwenbin.blog.v201801.web.BaseController;
import me.wuwenbin.modules.jpa.support.Page;
import me.wuwenbin.modules.pagination.model.layui.LayuiTable;
import me.wuwenbin.modules.utils.http.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import static me.wuwenbin.modules.utils.web.Controllers.builder;

/**
 * created by Wuwenbin on 2018/1/25 at 14:28
 */
@Controller
@RequestMapping("/management/comment")
public class CommentController extends BaseController {

    @Autowired
    private CommentRepository commentRepository;

    @GetMapping
    public String index() {
        return "management/comment";
    }

    @PostMapping("/list")
    @ResponseBody
    public LayuiTable<CommentVo> page(Page<CommentVo> commentPage, CommentQueryBo commentBo) {
        commentPage = commentRepository.findPagination(commentPage, CommentVo.class, commentBo);
        return layuiTable(commentPage);
    }

    @PostMapping("/edit/enable")
    @ResponseBody
    public R editEnable(Long id, Boolean enable) {
        return builder("修改评论状态").exec(() -> commentRepository.updateEnableById(enable, id) == 1);
    }

}
