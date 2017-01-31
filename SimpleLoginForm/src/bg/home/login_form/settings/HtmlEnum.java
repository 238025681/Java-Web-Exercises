/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | HtmlEnum
 * and open the template in the editor.
 */
package bg.home.login_form.settings;

/**
 *
 * @author kalin
 */
public enum HtmlEnum {
    MAIN {
        @Override
        public String getHtmlFile() {
            return "../resources/html/main.html";
        }

    };

    public abstract String getHtmlFile();

}
