package gln.test.user.controller;

import gln.test.common.CommonHelper;
import gln.test.config.ApplicationContext;
import gln.test.user.model.UserModel;
import gln.test.user.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/users")
public class UserController {
    private UserService userService;
    private CommonHelper ch = new CommonHelper();

    UserController(UserService userService){
        this.userService = userService;
    }

    @PostMapping
    public ResponseEntity<?> doAddUserRsApi(@RequestBody UserModel request) {
        request = userService.createUser(request);
        request.setCreateAt(ch.dateToStr(request.getDtCreate()));
        request.setDtCreate(null);
        request.setDtUpdate(null);
        return ResponseEntity.status(HttpStatus.CREATED).body(request);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<?> doUpdateUserRsApi(@PathVariable Integer id, @RequestBody Map<Object, Object> fields) {
        UserModel res;
        res = userService.updateUser(id,fields);
        res.setUpdateAt(ch.dateToStr(res.getDtUpdate()));
        res.setDtCreate(null);
        res.setDtUpdate(null);
        return ResponseEntity.status(HttpStatus.CREATED).body(res);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> doDeleteUserRsApi(@PathVariable Integer id) {
        userService.deleteUser(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> doGetUserRsApi(@PathVariable Integer id) {
        Map<String, Object> res = new HashMap<>();
        UserModel user;
        user = userService.getUser(id);
        if(null != user) {
            user.setCreateAt(ch.dateToStr(user.getDtCreate()));
            user.setUpdateAt(ch.dateToStr(user.getDtUpdate()));
            user.setDtCreate(null);
            user.setDtUpdate(null);
            res.put("data", user);
            return ResponseEntity.status(HttpStatus.OK).body(res);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("{}");
        }
    }

    @GetMapping
    public ResponseEntity<?> doGetUserListRsApi(@RequestParam Integer page) {
        Map<String, Object> res = userService.getUserList(page);
        List<UserModel> list = (List<UserModel>) res.get("data");
        list.forEach((u)->{
            u.setCreateAt(ch.dateToStr(u.getDtCreate()));
            u.setUpdateAt(ch.dateToStr(u.getDtUpdate()));
            u.setDtCreate(null);
            u.setDtUpdate(null);
        });
        return ResponseEntity.status(HttpStatus.OK).body(res);
    }
}
