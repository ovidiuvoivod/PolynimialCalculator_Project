package ro.utcluj;

import ro.utcluj.Controllers.MainController;
import ro.utcluj.Views.MainView;

public class App {
    public static void main(String[] args){
        MainView mainView= new MainView();
        MainController mainController=new MainController(mainView);
    }
}
