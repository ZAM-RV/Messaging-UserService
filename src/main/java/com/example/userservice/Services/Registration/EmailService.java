package com.example.userservice.Services.Registration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.IOException;

@Service
public class EmailService {

    private JavaMailSender javaMailSender;

    @Autowired
    public EmailService(JavaMailSender javaMailSender) {
        this.javaMailSender = javaMailSender;
    }

    public void sendEmailWithAttachment(String email, String verificationCode) throws MessagingException, IOException {

        MimeMessage msg = javaMailSender.createMimeMessage();

        MimeMessageHelper helper = new MimeMessageHelper(msg, true);

        helper.setTo(email);

        helper.setSubject("Testing from Spring boot");

        helper.setText("<!DOCTYPE html PUBLIC \"-//W3C//DTD XHTML 1.0 Transitional//EN\" \"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd\">\n" +
                "<html xmlns=\"http://www.w3.org/1999/xhtml\">\n" +
                "<head>\n" +
                "  <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\" />\n" +
                "  <meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\" />\n" +
                "  <title>Verify your email address</title>\n" +
                "  <style type=\"text/css\" rel=\"stylesheet\" media=\"all\">\n" +
                "    /* Base ------------------------------ */\n" +
                "    *:not(br):not(tr):not(html) {\n" +
                "      font-family: Arial, 'Helvetica Neue', Helvetica, sans-serif;\n" +
                "      -webkit-box-sizing: border-box;\n" +
                "      box-sizing: border-box;\n" +
                "    }\n" +
                "    body {\n" +
                "      width: 100% !important;\n" +
                "      height: 100%;\n" +
                "      margin: 0;\n" +
                "      line-height: 1.4;\n" +
                "      background-color: #F5F7F9;\n" +
                "      color: #839197;\n" +
                "      -webkit-text-size-adjust: none;\n" +
                "    }\n" +
                "    a {\n" +
                "      color: #414EF9;\n" +
                "    }\n" +
                "\n" +
                "    /* Layout ------------------------------ */\n" +
                "    .email-wrapper {\n" +
                "      width: 100%;\n" +
                "      margin: 0;\n" +
                "      padding: 0;\n" +
                "      background-color: #F5F7F9;\n" +
                "    }\n" +
                "    .email-content {\n" +
                "      width: 100%;\n" +
                "      margin: 0;\n" +
                "      padding: 0;\n" +
                "    }\n" +
                "\n" +
                "    /* Masthead ----------------------- */\n" +
                "    .email-masthead {\n" +
                "      padding: 25px 0;\n" +
                "      text-align: center;\n" +
                "    }\n" +
                "    .email-masthead_logo {\n" +
                "      max-width: 400px;\n" +
                "      border: 0;\n" +
                "    }\n" +
                "    .email-masthead_name {\n" +
                "      font-size: 16px;\n" +
                "      font-weight: bold;\n" +
                "      color: #839197;\n" +
                "      text-decoration: none;\n" +
                "      text-shadow: 0 1px 0 white;\n" +
                "    }\n" +
                "\n" +
                "    /* Body ------------------------------ */\n" +
                "    .email-body {\n" +
                "      width: 100%;\n" +
                "      margin: 0;\n" +
                "      padding: 0;\n" +
                "      border-top: 1px solid #E7EAEC;\n" +
                "      border-bottom: 1px solid #E7EAEC;\n" +
                "      background-color: #FFFFFF;\n" +
                "    }\n" +
                "    .email-body_inner {\n" +
                "      width: 570px;\n" +
                "      margin: 0 auto;\n" +
                "      padding: 0;\n" +
                "    }\n" +
                "    .email-footer {\n" +
                "      width: 570px;\n" +
                "      margin: 0 auto;\n" +
                "      padding: 0;\n" +
                "      text-align: center;\n" +
                "    }\n" +
                "    .email-footer p {\n" +
                "      color: #839197;\n" +
                "    }\n" +
                "    .body-action {\n" +
                "      width: 100%;\n" +
                "      margin: 30px auto;\n" +
                "      padding: 0;\n" +
                "      text-align: center;\n" +
                "    }\n" +
                "    .body-sub {\n" +
                "      margin-top: 25px;\n" +
                "      padding-top: 25px;\n" +
                "      border-top: 1px solid #E7EAEC;\n" +
                "    }\n" +
                "    .content-cell {\n" +
                "      padding: 35px;\n" +
                "    }\n" +
                "    .align-right {\n" +
                "      text-align: right;\n" +
                "    }\n" +
                "\n" +
                "    /* Type ------------------------------ */\n" +
                "    h1 {\n" +
                "      margin-top: 0;\n" +
                "      color: #292E31;\n" +
                "      font-size: 19px;\n" +
                "      font-weight: bold;\n" +
                "      text-align: left;\n" +
                "    }\n" +
                "    h2 {\n" +
                "      margin-top: 0;\n" +
                "      color: #292E31;\n" +
                "      font-size: 16px;\n" +
                "      font-weight: bold;\n" +
                "      text-align: left;\n" +
                "    }\n" +
                "    h3 {\n" +
                "      margin-top: 0;\n" +
                "      color: #292E31;\n" +
                "      font-size: 14px;\n" +
                "      font-weight: bold;\n" +
                "      text-align: left;\n" +
                "    }\n" +
                "    p {\n" +
                "      margin-top: 0;\n" +
                "      color: #839197;\n" +
                "      font-size: 16px;\n" +
                "      line-height: 1.5em;\n" +
                "      text-align: left;\n" +
                "    }\n" +
                "    p.sub {\n" +
                "      font-size: 12px;\n" +
                "    }\n" +
                "    p.center {\n" +
                "      text-align: center;\n" +
                "    }\n" +
                "    .image-masthead{\n" +
                "      width: 100%;\n" +
                "    }\n" +
                "    .text-code{\n" +
                "      text-align: center;\n" +
                "    }\n" +
                "\n" +
                "    /* Buttons ------------------------------ */\n" +
                "    .button {\n" +
                "      display: inline-block;\n" +
                "      width: 200px;\n" +
                "      background-color: #414EF9;\n" +
                "      border-radius: 3px;\n" +
                "      color: #ffffff;\n" +
                "      font-size: 15px;\n" +
                "      line-height: 45px;\n" +
                "      text-align: center;\n" +
                "      text-decoration: none;\n" +
                "      -webkit-text-size-adjust: none;\n" +
                "      mso-hide: all;\n" +
                "    }\n" +
                "    .button--green {\n" +
                "      background-color: #28DB67;\n" +
                "    }\n" +
                "    .button--red {\n" +
                "      background-color: #FF3665;\n" +
                "    }\n" +
                "    .button--blue {\n" +
                "      background-color: #414EF9;\n" +
                "    }\n" +
                "\n" +
                "    /*Media Queries ------------------------------ */\n" +
                "    @media only screen and (max-width: 600px) {\n" +
                "      .email-body_inner,\n" +
                "      .email-footer {\n" +
                "        width: 100% !important;\n" +
                "      }\n" +
                "    }\n" +
                "    @media only screen and (max-width: 500px) {\n" +
                "      .button {\n" +
                "        width: 100% !important;\n" +
                "      }\n" +
                "    }\n" +
                "  </style>\n" +
                "</head>\n" +
                "<body>\n" +
                "  <table class=\"email-wrapper\" width=\"100%\" cellpadding=\"0\" cellspacing=\"0\">\n" +
                "    <tr>\n" +
                "      <td align=\"center\">\n" +
                "        <table class=\"email-content\" width=\"100%\" cellpadding=\"0\" cellspacing=\"0\">\n" +
                "          <!-- Logo -->\n" +
                "          <tr>\n" +
                "            <td class=\"email-masthead\">\n" +
                "              <img class=\"image-masthead\" src=\"https://cdn2.wanderlust.co.uk/media/1002/cropped-header.jpg?anchor=center&mode=crop&width=600&height=225&format=auto&quality=30&rnd=131656621890000000\">\n" +
                "            </td>\n" +
                "          </tr>\n" +
                "          <!-- Email Body -->\n" +
                "          <tr>\n" +
                "            <td class=\"email-body\" width=\"100%\">\n" +
                "              <table class=\"email-body_inner\" align=\"center\" width=\"570\" cellpadding=\"0\" cellspacing=\"0\">\n" +
                "                <!-- Body content -->\n" +
                "                <tr>\n" +
                "                  <td class=\"content-cell\">\n" +
                "                    <h1>Validation Code</h1>\n" +
                "                    <p>Thanks for signing up for Messigo! We're excited to have you as an user. Please use this code to validate your account</p>\n" +
                "                    <!-- Action -->\n" +
                "                    <table class=\"body-action\" align=\"center\" width=\"100%\" cellpadding=\"0\" cellspacing=\"0\">\n" +
                "                      <tr>\n" +
                "                        <td align=\"center\">\n" +
                "                          <h1 class=\"text-code\">"+ verificationCode + "</h1>\n" +
                "                        </td>\n" +
                "                      </tr>\n" +
                "                    </table>\n" +
                "                    <p>Thanks,<br>The Messigo Team</p>\n" +
                "                    <!-- Sub copy -->\n" +
                "                  </td>\n" +
                "                </tr>\n" +
                "              </table>\n" +
                "            </td>\n" +
                "          </tr>\n" +
                "          <tr>\n" +
                "            <td>\n" +
                "              <table class=\"email-footer\" align=\"center\" width=\"570\" cellpadding=\"0\" cellspacing=\"0\">\n" +
                "                <tr>\n" +
                "                  <td class=\"content-cell\">\n" +
                "                    <p class=\"sub center\">\n" +
                "                      Messigo Labs, Inc.\n" +
                "                      <br>325 9th St, San Francisco, CA 94103\n" +
                "                    </p>\n" +
                "                  </td>\n" +
                "                </tr>\n" +
                "              </table>\n" +
                "            </td>\n" +
                "          </tr>\n" +
                "        </table>\n" +
                "      </td>\n" +
                "    </tr>\n" +
                "  </table>\n" +
                "</body>\n" +
                "</html>", true);

        javaMailSender.send(msg);
    }
}
