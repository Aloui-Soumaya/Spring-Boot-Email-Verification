package com.example.demo.Controller;

import com.example.demo.Repository.Repo;
import com.example.demo.Repository.TokenRepo;
import com.example.demo.Service.EmailService;
import com.example.demo.dto.UsersDto;
import com.example.demo.entity.ConfirmationToken;
import com.example.demo.entity.Users;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@RestController
@CrossOrigin("http://localhost:4200")
public class UserController {
    private final Repo repo ;
    private final ModelMapper mapper;
    private final TokenRepo tokenRepo;
    private EmailService emailService;

    @Autowired
    private JavaMailSender javaMailSender;
    @Autowired
    public UserController(Repo repo, ModelMapper mapper, TokenRepo tokenRepo,EmailService emailService) {
        this.repo = repo;
        this.mapper = mapper;
        this.tokenRepo = tokenRepo;
        this.emailService = emailService;
    }
    @PostMapping
    public Users create(@RequestBody UsersDto usersDto ) throws MessagingException {
        if(usersDto==null){
            throw new NoSuchElementException();
        }
        usersDto.setEtatCompte(false);
        Users result = repo.save(mapper.map(usersDto, Users.class));
        ConfirmationToken confirmationToken = new ConfirmationToken(result);
        tokenRepo.save(confirmationToken);
        // de ici
        String lien="http://localhost:8085/confirm-account?token="+confirmationToken.getConfirmationToken();
        MimeMessage msg = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(msg, true);
        helper.setTo(usersDto.getEmail());
        helper.setSubject("Votre accès à votre compte SmartClinic ");
        helper.setText("<h3>Bonjour "+usersDto.getNom()+" "+usersDto.getPrenom()+" , </h3>" +
                            "<p>Bienvenu dans SmartClinic , </p></br> " +
                            "<p>Nous sommes très heureux que vous ayez accepté de rejoindre notre clinique et nous sommes impatients de vous compter parmi nous.</p>"+
                            "<p> Vous pouvez confirmer et activer l'accès à votre compte en cliquant sur  :<p> "+ "<a href="+lien+">ce lien</a></br><p>A trés bientot,<p></br><p>SmartClinic</p>", true);
        javaMailSender.send(msg);
        /*SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setTo(usersDto.getEmail());
        mailMessage.setSubject("Votre accès à votre compte SmartClinic ");
        mailMessage.setText("<p>Bonjour , </p></br> Bienvenu dans SmartClinic , </br> " +
                "Vous pouvez confirmer et activer l'accès à votre compte en cliquant sur ce lien : "
                +"http://localhost:8085/confirm-account?token="+confirmationToken.getConfirmationToken()+"\nA trés bientot,\nSmartClinic");

        emailService.sendEmail(mailMessage);*/
        return result;
    }
    @GetMapping("/all")
    public List<Users> findAll(){
        List<Users> all = repo.findAll();
        if(all.isEmpty()){
            throw new NoSuchElementException();
        }else{
            return all;
        }
    }
    @RequestMapping(value="/confirm-account", method= {RequestMethod.GET, RequestMethod.POST})
    public RedirectView confirmUserAccount(@RequestParam("token")String confirmationToken)
    {
        ConfirmationToken token = tokenRepo.findByConfirmationToken(confirmationToken);
        if(token != null)
        {
            Users u=repo.findUsersByEmail(token.getUser().getEmail()).orElseThrow(()->new NoSuchElementException("user doesn't exist"));
            u.setEtatCompte(true);
            repo.save(u);
        }
        RedirectView redirectView = new RedirectView();
        redirectView.setUrl("http://www.yahoo.com");
        return redirectView;
    }
}
