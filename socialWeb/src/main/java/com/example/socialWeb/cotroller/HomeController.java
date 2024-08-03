package com.example.socialWeb.cotroller;

import com.example.socialWeb.DTOS.UserDTO;
import com.example.socialWeb.models.Posts;
import com.example.socialWeb.models.Replies;
import com.example.socialWeb.models.User;
import com.example.socialWeb.services.PostsServices;
import com.example.socialWeb.services.RepliesServices;
import com.example.socialWeb.services.UserServices;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/")
public class HomeController {
    private UserServices userServices;
    private PasswordEncoder passwordEncoder;
    private PostsServices postsServices;
    private RepliesServices repliesServices;
    private String userUserName="";



    public HomeController(UserServices userServices, PasswordEncoder passwordEncoder, PostsServices postsServices, RepliesServices repliesServices) {
        this.userServices = userServices;
        this.passwordEncoder = passwordEncoder;
        this.postsServices = postsServices;
        this.repliesServices = repliesServices;
    }

    @GetMapping("/login")
    public String getLogin(){

        return "/login";
    }

    @GetMapping("/register")
    public String register(Model model){
        model.addAttribute("userDTO",new UserDTO());
        return "/register";
    }
    @GetMapping("/loginsuccses")
    public String loginsuccses(@RequestParam String username,Model model){
        userUserName=username;
        model.addAttribute("posts",postsServices.findAll());
        String word="";
        model.addAttribute("word",word);
        model.addAttribute("user",userServices.findByUserName(username));
        return "/mainpage";
    }

    @PostMapping("register")
    public String postRegister(@ModelAttribute("userDTO") UserDTO userDTO){
        System.out.println(userDTO.toString());
        if(userServices.checkUsername(userDTO.getUser_name())==null) {
            userDTO.setPassword(passwordEncoder.encode(userDTO.getPassword()));
            userServices.add(userDTO);
            return "/login";
        }
       else{
           return "/register";}
    }

    @GetMapping("/home")
    public String home(){
        return "/home";
    }

    @GetMapping("mainpage")
    public String mainPage(Model model){
        model.addAttribute("posts",postsServices.findAll());
        String word="";
        model.addAttribute("word",word);
        model.addAttribute("user",userServices.findByUserName(userUserName));
        return "/mainpage";
    }
    @GetMapping("/addpost")
    public String addPost(Model model){
        model.addAttribute("post",new Posts());
        return "/addpost";
    }
    @PostMapping("/addpost")
    public String postaddpost(@ModelAttribute("post") Posts post){

        post.setUser(userServices.findByUserName(userUserName));
        post.setUp(0);
        post.setDown(0);
        postsServices.add(post);
        return "redirect:/mainpage";
    }

    @GetMapping("/replies")
    public String replies(){
        return "/replies";
    }


    @GetMapping("/addlike")
    public String addlike(@RequestParam long id)
    {
        System.out.println("like");
        postsServices.addUp(id);
        return "redirect:/mainpage";
    }

    @GetMapping("/dislike")
    public String dislike(@RequestParam long id)
    {
        postsServices.addDown(id);
        return "redirect:/mainpage";
    }

    @GetMapping("/replaytopost")
    public String replaytopost(@RequestParam long id,Model model){
       model.addAttribute("postid",id);
        model.addAttribute("replay",new Replies());
        return "/replaytopost";
    }
    @PostMapping("/addedreplay")
    public String addedreplay(@ModelAttribute("replay") Replies replies,@RequestParam long id){
        replies.setUserReplay(userServices.findByUserName(userUserName));
        replies.setPost(new Posts(id));
        repliesServices.add(replies);
        return "redirect:mainpage";
    }

    @GetMapping("/viewReplies")
    private String viewReplies(@RequestParam long id,Model model){
        model.addAttribute("replies",repliesServices.findAll(id));
        model.addAttribute("postid",postsServices.getById(id));
        return "/replies";
    }
    @GetMapping("/searchpost")
    public String searchpost(@ModelAttribute("word") String word,Model model){

        if(word.isEmpty()){
            model.addAttribute("posts",postsServices.findAll());
            model.addAttribute("word",word);
        }
        model.addAttribute("posts",postsServices.searchGenre(word));
        model.addAttribute("word",word);
        model.addAttribute("user",userServices.findByUserName(userUserName));
        return "/mainpage";
    }
    @GetMapping("/editpost")
    public String editpost(@RequestParam long id,Model model){
        model.addAttribute("post",postsServices.getById(id));
        return "/editpost";
    }
    @PostMapping("/editpost")
    public String posteditpost(@ModelAttribute("post") Posts post,@RequestParam("id")  int userid){
        System.out.println(userid);
        post.setUser(new User(userid));
        postsServices.add(post);
        return "redirect:mainpage";
    }

}
