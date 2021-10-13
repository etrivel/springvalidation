package com.example.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.example.entity.EntityModel;
import com.example.entity.TokenClass;
import com.example.repository.EntityRepository;
import com.example.repository.TokenRepository;
import com.example.service.JavaEmail;

@CrossOrigin
@Controller
public class UserController {

    @Autowired
    private EntityRepository entityRepository;

    @Autowired
    private TokenRepository TokenRepository;

    @Autowired
    private JavaEmail emailService;

    @RequestMapping(value="/user", method = RequestMethod.GET)
    public ModelAndView displayRegistration(ModelAndView modelAndView, EntityModel userEntity)
    {
        modelAndView.addObject("userEntity", userEntity);
        modelAndView.setViewName("user");
        return modelAndView;
    }
    
    
    
    @RequestMapping(value="/user", method = RequestMethod.POST)
    public ModelAndView registerUser(ModelAndView modelAndView, EntityModel entityModel)
    {

    	EntityModel existingUser = entityRepository.findByEmailIdIgnoreCase(entityModel.getEmailId());
        if(existingUser != null)
        {
            modelAndView.addObject("message","This email already exists!");
            modelAndView.setViewName("error");
        }
        else
        {
           entityRepository.save(entityModel);

          TokenClass confirmationToken = new TokenClass(entityModel);

           TokenRepository.save(confirmationToken);

            SimpleMailMessage mailMessage = new SimpleMailMessage();
            mailMessage.setTo(entityModel.getEmailId());
            mailMessage.setSubject("Complete Registration!");
            mailMessage.setFrom("YOUR EMAIL ADDRESS");
            mailMessage.setText("To confirm your account, please click here : "
            +"http://localhost:8080/confirm-account?token="+confirmationToken.getConfirmationToken());

            emailService.sendEmail(mailMessage);

            modelAndView.addObject("emailId", entityModel.getEmailId());

            modelAndView.setViewName("successfulRegisteration");
        }

        return modelAndView;
    }
    

    @RequestMapping(value="/confirm-account", method= {RequestMethod.GET, RequestMethod.POST})
    public ModelAndView confirmUserAccount(ModelAndView modelAndView, @RequestParam("token")String confirmationToken)
    {
       TokenClass token = TokenRepository.findByConfirmationToken(confirmationToken);

        if(token != null)
        {
        	EntityModel user = entityRepository.findByEmailIdIgnoreCase(token.getEntityModel().getEmailId());
            user.setEnabled(true);
           entityRepository.save(user);
            modelAndView.setViewName("accountVerified");
        }
        else
        {
            modelAndView.addObject("message","The link is invalid or broken!");
            modelAndView.setViewName("error");
        }

        return modelAndView;
    }
}
