package com.hmdp.controller;


import com.hmdp.dto.Result;
import com.hmdp.service.IFollowService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.print.DocFlavor;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author 虎哥
 * @since 2021-12-22
 */
@RestController
@RequestMapping("/follow")
public class FollowController {

    @Resource
    private IFollowService followService;

    @RequestMapping(value = "/{id}/{isFollow}", method = RequestMethod.PUT)
    public Result follow(@PathVariable("id")Long followUserId,@PathVariable("isFollow") Boolean isFollow) {
        return followService.follow(followUserId,isFollow);
    }

    @RequestMapping(value = "/or/not/{id}",method = RequestMethod.GET)
    public Result isFollow(@PathVariable("id")Long followUserId) {
        return followService.isFollow(followUserId);
    }

    @GetMapping("/common/{id}")
    public Result followCommons(@PathVariable("id") Long id) {
        return followService.followCommons(id);
    }
}
