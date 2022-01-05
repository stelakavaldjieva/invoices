package bg.startit.invoices.web.controller;

import bg.startit.invoices.service.UserService;
import bg.startit.invoices.utils.EncrytedPasswordUtils;
import bg.startit.invoices.utils.WebUtils;
import bg.startit.invoices.web.dto.UserDto;
import bg.startit.invoices.web.dto.UserListDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.security.Principal;
import java.time.LocalDateTime;
import java.util.stream.Collectors;

@Controller
public class MainController {

    private final static Logger LOGGER = LoggerFactory.getLogger(UserDetailsService.class);

    private final UserService userService;

    public MainController(UserService userService) {

        this.userService = userService;
    }

    @RequestMapping(value = {"/", "/welcome"}, method = RequestMethod.GET)
    public String welcomePage(Model model) {
        model.addAttribute("title", "Welcome");
        model.addAttribute("message", "This is welcome page!");
        return "welcomePage";
    }

    @RequestMapping(value = "/admin", method = RequestMethod.GET)
    public String adminPage(Model model, Principal principal) {

        User loggedUser = (User) ((Authentication) principal).getPrincipal();

        String userInfo = WebUtils.toString(loggedUser);
        model.addAttribute("userInfo", userInfo);

        return "adminPage";
    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String loginPage(Model model) {

        return "loginPage";
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public String register(@ModelAttribute("user") @Valid final UserDto userDto, final HttpServletRequest request, final Errors errors) throws Exception {

        LOGGER.debug("Registering user account with information: {}", userDto);

        bg.startit.invoices.persistence.model.User user = new bg.startit.invoices.persistence.model.User();
        BeanUtils.copyProperties(userDto, user);

        user.setPassword(EncrytedPasswordUtils.encrytePassword(userDto.getPassword()));
        user.setLast_login_date(LocalDateTime.now());
        // By default user has status NOT_VALID
        user.setStatus(1L);
        // By default user has type NOT_VALID
        user.setType(1L);

        if (userService.checkEmailExist(user.getEmail())){
            return "loginPage";
        }

        if (userService.checkUsernameExist(user.getUsername())) {
            return "loginPage";
        }

        userService.registerUser(user);

        return "loginPage";
    }

    @RequestMapping(value = "/logoutSuccessful", method = RequestMethod.GET)
    public String logoutSuccessfulPage(Model model) {

        model.addAttribute("title", "Logout");

        return "logoutSuccessfulPage";
    }

    @RequestMapping(value = "/userInfo", method = RequestMethod.GET)
    public String userInfo(Model model, Principal principal) {

        // (1) (en)
        // After user login successfully.
        String userName = principal.getName();

        System.out.println("User Name: " + userName);

        User loggedUser = (User) ((Authentication) principal).getPrincipal();

        String userInfo = WebUtils.toString(loggedUser);
        model.addAttribute("userInfo", userInfo);

        return "userInfoPage";
    }

    @GetMapping("/allUsers")
    public String showAllUsers(Model model) {
        // Default pagination to lower the foot print
        Pageable pageable = PageRequest.of(0, 20);

        model.addAttribute("users", userService.findAll(pageable));

        return "users/allUsers";
    }

    @RequestMapping(value = "/403", method = RequestMethod.GET)
    public String accessDenied(Model model, Principal principal) {

        if (principal != null) {

            User loggedUser = (User) ((Authentication) principal).getPrincipal();

            String userInfo = WebUtils.toString(loggedUser);

            model.addAttribute("userInfo", userInfo);

            String message = "Hi " + principal.getName() //
                    + "<br> You do not have permission to access this page!";
            model.addAttribute("message", message);
        }

        return "403Page";
    }

    private UserListDto mapUserToUserListDto(Integer pageNumber, Integer pageSize, Page<bg.startit.invoices.persistence.model.User> pageOfUser) {

        return new UserListDto(
                pageNumber,
                pageSize,
                pageOfUser.getNumberOfElements(),
                (int) pageOfUser.getTotalElements(),

                // Map the car list to content list of car DTO.
                pageOfUser.getContent()
                        .stream()
                        .map(user -> new UserDto(
                                user.getId(),
                                user.getUsername(),
                                user.getEmail(),
                                user.getPassword(),
                                user.getType(),
                                user.getLast_login_date(),
                                user.getStatus()
                        )).collect(Collectors.toList()));
    }
}
