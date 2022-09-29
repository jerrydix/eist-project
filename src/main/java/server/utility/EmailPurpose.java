package server.utility;

public enum EmailPurpose {
    REGISTRATION, LOGIN, BOOKING, FUNDS, EXCHANGE;

    private static final String[] subjects = {
            "Registration Confirmation",
            "Login Alert",
            "Booking Confirmation",
            "Confirmation of adding funds",
            "Confirmation or reward exchange",
    };
    private final String website = "http://localhost:8080/#/";
    private final String address = "Garching Airlines Inc, Bolzmannstrasse 101, 85748 Garching b. München, Germany";

    public int getId() {
        return switch (this) {
            case REGISTRATION -> 0;
            case LOGIN -> 1;
            case BOOKING -> 2;
            case FUNDS -> 3;
            case EXCHANGE -> 4;
        };
    }

    public String getSubject() {
        return switch (this) {
            case REGISTRATION -> subjects[0];
            case LOGIN -> subjects[1];
            case BOOKING -> subjects[2];
            case FUNDS -> subjects[3];
            case EXCHANGE -> subjects[4];
        };
    }

    public String getContent(String[] additionalContent) {
        return switch (this) {

            case REGISTRATION -> "<!doctype html>\n" +
                    "<html xmlns=\"http://www.w3.org/1999/xhtml\" xmlns:v=\"urn:schemas-microsoft-com:vml\" xmlns:o=\"urn:schemas-microsoft-com:office:office\">\n" +
                    "\n" +
                    "<head>\n" +
                    "    <title>\n" +
                    "\n" +
                    "    </title>\n" +
                    "    <!--[if !mso]><!-- -->\n" +
                    "    <meta http-equiv=\"X-UA-Compatible\" content=\"IE=edge\">\n" +
                    "    <!--<![endif]-->\n" +
                    "    <meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\">\n" +
                    "    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1\">\n" +
                    "    <style type=\"text/css\">\n" +
                    "        #outlook a {\n" +
                    "            padding: 0;\n" +
                    "        }\n" +
                    "\n" +
                    "        .ReadMsgBody {\n" +
                    "            width: 100%;\n" +
                    "        }\n" +
                    "\n" +
                    "        .ExternalClass {\n" +
                    "            width: 100%;\n" +
                    "        }\n" +
                    "\n" +
                    "        .ExternalClass * {\n" +
                    "            line-height: 100%;\n" +
                    "        }\n" +
                    "\n" +
                    "        body {\n" +
                    "            margin: 0;\n" +
                    "            padding: 0;\n" +
                    "            -webkit-text-size-adjust: 100%;\n" +
                    "            -ms-text-size-adjust: 100%;\n" +
                    "        }\n" +
                    "\n" +
                    "        table,\n" +
                    "        td {\n" +
                    "            border-collapse: collapse;\n" +
                    "            mso-table-lspace: 0pt;\n" +
                    "            mso-table-rspace: 0pt;\n" +
                    "        }\n" +
                    "\n" +
                    "        img {\n" +
                    "            border: 0;\n" +
                    "            height: auto;\n" +
                    "            line-height: 100%;\n" +
                    "            outline: none;\n" +
                    "            text-decoration: none;\n" +
                    "            -ms-interpolation-mode: bicubic;\n" +
                    "        }\n" +
                    "\n" +
                    "        p {\n" +
                    "            display: block;\n" +
                    "            margin: 13px 0;\n" +
                    "        }\n" +
                    "    </style>\n" +
                    "    <!--[if !mso]><!-->\n" +
                    "    <style type=\"text/css\">\n" +
                    "        @media only screen and (max-width:480px) {\n" +
                    "            @-ms-viewport {\n" +
                    "                width: 320px;\n" +
                    "            }\n" +
                    "            @viewport {\n" +
                    "                width: 320px;\n" +
                    "            }\n" +
                    "        }\n" +
                    "    </style>\n" +
                    "    <!--<![endif]-->\n" +
                    "    <!--[if mso]>\n" +
                    "        <xml>\n" +
                    "        <o:OfficeDocumentSettings>\n" +
                    "          <o:AllowPNG/>\n" +
                    "          <o:PixelsPerInch>96</o:PixelsPerInch>\n" +
                    "        </o:OfficeDocumentSettings>\n" +
                    "        </xml>\n" +
                    "        <![endif]-->\n" +
                    "    <!--[if lte mso 11]>\n" +
                    "        <style type=\"text/css\">\n" +
                    "          .outlook-group-fix { width:100% !important; }\n" +
                    "        </style>\n" +
                    "        <![endif]-->\n" +
                    "\n" +
                    "\n" +
                    "    <style type=\"text/css\">\n" +
                    "        @media only screen and (min-width:480px) {\n" +
                    "            .mj-column-per-100 {\n" +
                    "                width: 100% !important;\n" +
                    "            }\n" +
                    "        }\n" +
                    "    </style>\n" +
                    "\n" +
                    "\n" +
                    "    <style type=\"text/css\">\n" +
                    "    </style>\n" +
                    "\n" +
                    "</head>\n" +
                    "\n" +
                    "<body style=\"background-color:#f9f9f9;\">\n" +
                    "\n" +
                    "\n" +
                    "    <div style=\"background-color:#f9f9f9;\">\n" +
                    "\n" +
                    "\n" +
                    "        <!--[if mso | IE]>\n" +
                    "      <table\n" +
                    "         align=\"center\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" style=\"width:600px;\" width=\"600\"\n" +
                    "      >\n" +
                    "        <tr>\n" +
                    "          <td style=\"line-height:0px;font-size:0px;mso-line-height-rule:exactly;\">\n" +
                    "      <![endif]-->\n" +
                    "\n" +
                    "\n" +
                    "        <div style=\"background:#f9f9f9;background-color:#f9f9f9;Margin:0px auto;max-width:600px;\">\n" +
                    "\n" +
                    "            <table align=\"center\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" role=\"presentation\" style=\"background:#f9f9f9;background-color:#f9f9f9;width:100%;\">\n" +
                    "                <tbody>\n" +
                    "                    <tr>\n" +
                    "                        <td style=\"border-bottom:#2572ba solid 5px;direction:ltr;font-size:0px;padding:20px 0;text-align:center;vertical-align:top;\">\n" +
                    "                            <!--[if mso | IE]>\n" +
                    "                  <table role=\"presentation\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\">\n" +
                    "                \n" +
                    "        <tr>\n" +
                    "      \n" +
                    "        </tr>\n" +
                    "      \n" +
                    "                  </table>\n" +
                    "                <![endif]-->\n" +
                    "                        </td>\n" +
                    "                    </tr>\n" +
                    "                </tbody>\n" +
                    "            </table>\n" +
                    "\n" +
                    "        </div>\n" +
                    "\n" +
                    "\n" +
                    "        <!--[if mso | IE]>\n" +
                    "          </td>\n" +
                    "        </tr>\n" +
                    "      </table>\n" +
                    "      \n" +
                    "      <table\n" +
                    "         align=\"center\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" style=\"width:600px;\" width=\"600\"\n" +
                    "      >\n" +
                    "        <tr>\n" +
                    "          <td style=\"line-height:0px;font-size:0px;mso-line-height-rule:exactly;\">\n" +
                    "      <![endif]-->\n" +
                    "\n" +
                    "\n" +
                    "        <div style=\"background:#239ade;background-color:#239ade;Margin:0px auto;max-width:600px;\">\n" +
                    "\n" +
                    "            <table align=\"center\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" role=\"presentation\" style=\"background:#fff;background-color:#fff;width:100%;\">\n" +
                    "                <tbody>\n" +
                    "                    <tr>\n" +
                    "                        <td style=\"border:#dddddd solid 1px;border-top:0px;direction:ltr;font-size:0px;padding:20px 0;text-align:center;vertical-align:top;\">\n" +
                    "                            <!--[if mso | IE]>\n" +
                    "                  <table role=\"presentation\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\">\n" +
                    "                \n" +
                    "        <tr>\n" +
                    "      \n" +
                    "            <td\n" +
                    "               style=\"vertical-align:bottom;width:600px;\"\n" +
                    "            >\n" +
                    "          <![endif]-->\n" +
                    "\n" +
                    "                            <div class=\"mj-column-per-100 outlook-group-fix\" style=\"font-size:13px;text-align:left;direction:ltr;display:inline-block;vertical-align:bottom;width:100%;\">\n" +
                    "\n" +
                    "                                <table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" role=\"presentation\" style=\"vertical-align:bottom;\" width=\"100%\">\n" +
                    "\n" +
                    "                                    <tr>\n" +
                    "                                        <td align=\"center\" style=\"font-size:0px;padding:10px 25px;word-break:break-word;\">\n" +
                    "\n" +
                    "                                            <table align=\"center\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" role=\"presentation\" style=\"border-collapse:collapse;border-spacing:0px;\">\n" +
                    "                                                <tbody>\n" +
                    "                                                    <tr>\n" +
                    "                                                        <td style=\"width:64px;\">\n" +
                    "\n" +
                    "                                                            <img height=\"auto\" src=\"https://i.ibb.co/jfg29pX/logo.png\" style=\"border:0;display:block;outline:none;text-decoration:none;width:100%;\" width=\"64\" />\n" +
                    "\n" +
                    "                                                        </td>\n" +
                    "                                                    </tr>\n" +
                    "                                                </tbody>\n" +
                    "                                            </table>\n" +
                    "\n" +
                    "                                        </td>\n" +
                    "                                    </tr>\n" +
                    "\n" +
                    "                                    <tr>\n" +
                    "                                        <td align=\"center\" style=\"font-size:0px;padding:10px 25px;padding-bottom:40px;word-break:break-word;\">\n" +
                    "\n" +
                    "                                            <div style=\"font-family:'Helvetica Neue',Arial,sans-serif;font-size:28px;font-weight:bold;line-height:1;text-align:center;color:#555;\">\n" +
                    "                                                Thank you for registering at Garching Airlines\n" +
                    "                                            </div>\n" +
                    "\n" +
                    "                                        </td>\n" +
                    "                                    </tr>\n" +
                    "\n" +
                    "                                    <tr>\n" +
                    "                                        <td align=\"left\" style=\"font-size:0px;padding:10px 25px;word-break:break-word;\">\n" +
                    "\n" +
                    "                                            <div style=\"font-family:'Helvetica Neue',Arial,sans-serif;font-size:16px;line-height:22px;text-align:left;color:#555;\">\n" +
                    "                                                Hello " + additionalContent[0] + "!<br></br>\n" +
                    "                                                Thank you for signing up to our website. We're really happy to have you! To login for the first time, please use this code when prompted:\n" +
                    "                                            </div>\n" +
                    "\n" +
                    "                                        </td>\n" +
                    "                                    </tr>\n" +
                    "\n" +
                    "                                    <tr>\n" +
                    "                                        <td align=\"center\" style=\"font-size:0px;padding:10px 25px;padding-top:30px;padding-bottom:50px;word-break:break-word;\">\n" +
                    "\n" +
                    "                                            <table align=\"center\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" role=\"presentation\" style=\"border-collapse:separate;line-height:100%;\">\n" +
                    "                                                <tr>\n" +
                    "                                                    <td align=\"center\" bgcolor=\"#37a34b\" role=\"presentation\" style=\"border:none;border-radius:3px;color:#ffffff;cursor:auto;padding:15px 25px;\" valign=\"middle\">\n" +
                    "                                                        <p style=\"background:#37a34b;color:#ffffff;font-family:'Helvetica Neue',Arial,sans-serif;font-size:15px;font-weight:normal;line-height:120%;Margin:0;text-decoration:none;text-transform:none;\">\n" +
                    "                                                            " + additionalContent[1] + "\n" +
                    "                                                        </p>\n" +
                    "                                                    </td>\n" +
                    "                                                </tr>\n" +
                    "                                            </table>\n" +
                    "\n" +
                    "                                        </td>\n" +
                    "                                    </tr>\n" +
                    "\n" +
                    "                                    <tr>\n" +
                    "                                        <td align=\"left\" style=\"font-size:0px;padding:10px 25px;word-break:break-word;\">\n" +
                    "\n" +
                    "                                            <div style=\"font-family:'Helvetica Neue',Arial,sans-serif;font-size:14px;line-height:20px;text-align:left;color:#525252;\">\n" +
                    "                                                Be excellent,<br><br> Garching Airlines Team<br>\n" +
                    "                                                <a href=\"" + website + "\" style=\"color:#2F67F6\">" + website + "</a>\n" +
                    "                                            </div>\n" +
                    "\n" +
                    "                                        </td>\n" +
                    "                                    </tr>\n" +
                    "\n" +
                    "                                </table>\n" +
                    "\n" +
                    "                            </div>\n" +
                    "\n" +
                    "                            <!--[if mso | IE]>\n" +
                    "            </td>\n" +
                    "          \n" +
                    "        </tr>\n" +
                    "      \n" +
                    "                  </table>\n" +
                    "                <![endif]-->\n" +
                    "                        </td>\n" +
                    "                    </tr>\n" +
                    "                </tbody>\n" +
                    "            </table>\n" +
                    "\n" +
                    "        </div>\n" +
                    "\n" +
                    "\n" +
                    "        <!--[if mso | IE]>\n" +
                    "          </td>\n" +
                    "        </tr>\n" +
                    "      </table>\n" +
                    "      \n" +
                    "      <table\n" +
                    "         align=\"center\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" style=\"width:600px;\" width=\"600\"\n" +
                    "      >\n" +
                    "        <tr>\n" +
                    "          <td style=\"line-height:0px;font-size:0px;mso-line-height-rule:exactly;\">\n" +
                    "      <![endif]-->\n" +
                    "\n" +
                    "\n" +
                    "        <div style=\"Margin:0px auto;max-width:600px;\">\n" +
                    "\n" +
                    "            <table align=\"center\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" role=\"presentation\" style=\"width:100%;\">\n" +
                    "                <tbody>\n" +
                    "                    <tr>\n" +
                    "                        <td style=\"direction:ltr;font-size:0px;padding:20px 0;text-align:center;vertical-align:top;\">\n" +
                    "                            <!--[if mso | IE]>\n" +
                    "                  <table role=\"presentation\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\">\n" +
                    "                \n" +
                    "        <tr>\n" +
                    "      \n" +
                    "            <td\n" +
                    "               style=\"vertical-align:bottom;width:600px;\"\n" +
                    "            >\n" +
                    "          <![endif]-->\n" +
                    "\n" +
                    "                            <div class=\"mj-column-per-100 outlook-group-fix\" style=\"font-size:13px;text-align:left;direction:ltr;display:inline-block;vertical-align:bottom;width:100%;\">\n" +
                    "\n" +
                    "                                <table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" role=\"presentation\" width=\"100%\">\n" +
                    "                                    <tbody>\n" +
                    "                                        <tr>\n" +
                    "                                            <td style=\"vertical-align:bottom;padding:0;\">\n" +
                    "\n" +
                    "                                                <table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" role=\"presentation\" width=\"100%\">\n" +
                    "\n" +
                    "                                                    <tr>\n" +
                    "                                                        <td align=\"center\" style=\"font-size:0px;padding:0;word-break:break-word;\">\n" +
                    "\n" +
                    "                                                            <div style=\"font-family:'Helvetica Neue',Arial,sans-serif;font-size:12px;font-weight:300;line-height:1;text-align:center;color:#575757;\">\n" +
                    "                                                                " + address + "\n" +
                    "                                                            </div>\n" +
                    "\n" +
                    "                                                        </td>\n" +
                    "                                                    </tr>\n" +
                    "\n" +
                    "\n" +
                    "                                                </table>\n" +
                    "\n" +
                    "                                            </td>\n" +
                    "                                        </tr>\n" +
                    "                                    </tbody>\n" +
                    "                                </table>\n" +
                    "\n" +
                    "                            </div>\n" +
                    "\n" +
                    "                            <!--[if mso | IE]>\n" +
                    "            </td>\n" +
                    "          \n" +
                    "        </tr>\n" +
                    "      \n" +
                    "                  </table>\n" +
                    "                <![endif]-->\n" +
                    "                        </td>\n" +
                    "                    </tr>\n" +
                    "                </tbody>\n" +
                    "            </table>\n" +
                    "\n" +
                    "        </div>\n" +
                    "\n" +
                    "\n" +
                    "        <!--[if mso | IE]>\n" +
                    "          </td>\n" +
                    "        </tr>\n" +
                    "      </table>\n" +
                    "      <![endif]-->\n" +
                    "\n" +
                    "\n" +
                    "    </div>\n" +
                    "\n" +
                    "</body>\n" +
                    "\n" +
                    "</html>";
            case LOGIN -> null;
            case BOOKING -> null;
            case FUNDS -> null;
            case EXCHANGE -> null;
        };
    }
}
