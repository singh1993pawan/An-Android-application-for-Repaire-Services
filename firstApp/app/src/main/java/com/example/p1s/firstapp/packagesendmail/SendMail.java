package com.example.p1s.firstapp.packagesendmail;


import android.util.Log;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class SendMail
{
    public static void send(final String from, final String password, String to, String sub, String msg)
    {
        //GET PROPERTIES OBJECT
        Properties pros=new Properties();
        pros.put("mail.smtp.host","smtp.gmail.com");
        pros.put("mail.smtp.socketFactory.port","465");
        pros.put("mail.smtp.socketFactory.class","javax.net.ssl.SSLSocketFactory");
        pros.put("mail.smtp.auth","true");
        pros.put("mail.smtp.port","465");


        //GET SESSION
        Session session=Session.getDefaultInstance(pros,new javax.mail.Authenticator()
                {
                    protected PasswordAuthentication getPasswordAuthentication()
                    {
                        return  new PasswordAuthentication(from,password);
                    }
                }
        );

        //Compose mail
        try
        {

            MimeMessage message=new MimeMessage(session);
            message.addRecipients(Message.RecipientType.TO , InternetAddress.parse(to));
            message.setSubject(sub);
            message.setText(msg);
            Transport.send(message);

        }
        catch (Exception e)
        {
            Log.d("ERROR OCCURED AT MAIL",e.toString());
            e.printStackTrace();
        }

    }


};
