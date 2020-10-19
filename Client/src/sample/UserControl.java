package sample;

import java.net.URL;
import java.util.ResourceBundle;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import Connectivity.ConnClass;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

public class UserControl implements Initializable
{
    public Button submit;
    public Label warn;
    public TextField name;
    public TextField email;
    public TextField phn;
    public ChoiceBox state;
    public DatePicker dob;

    public CheckBox pop;
    public CheckBox rap;
    public CheckBox contemp;
    public CheckBox metal;
    public CheckBox folk;
    public CheckBox romantic;
    public CheckBox hiphop;
    public CheckBox brostep;
    public CheckBox regional;
    public CheckBox band;
    public CheckBox rock;
    public CheckBox classical;

    public CheckBox english;
    public CheckBox hindi;
    public CheckBox telugu;
    public CheckBox harayanvi;
    public CheckBox bihari;
    public CheckBox punjabi;
    public CheckBox french;
    public CheckBox spanish;
    public CheckBox tamil;
    public CheckBox marathi;
    public CheckBox gujarati;
    public CheckBox rajasthani;


    @Override
    public void initialize(URL location, ResourceBundle resources)
    {
        String states[]={"Madhya Pradesh", "Uttar Pradesh", "bihar", "Punjab", "Harayana",
                "Chhattisgarh", "Gujarat", "Maharashtra", "Jammu & Kashmir", "Andhra Pradesh",
        "Telangana", "Uttar Pradesh", "Uttarakhand", "North-Eastern States", "Kerela", "Karnataka",
        "Tamil Nadu", "Odisha", "West Bengal"};
        warn.setText("");
        state.setItems(FXCollections.observableArrayList(states));
    }


    public void submitAct(ActionEvent event)
    {
        try {
            String name1 = name.getText();
            String phn1 = phn.getText();
            String email1 = email.getText();
            String state1 = state.getId();
            String dob1 = dob.getId();
            String pop1="", rap1="", classical1="", metal1="", contemp1="", folk1="", romantic1="", hiphop1="", brostep1="", regional1="", band1="", rock1="";
            String eng1="", hindi1="", telugu1="", harayanvi1="", bihari1="", punjabi1="", french1="", spanish1="", tamil1="", marathi1="", guj1="", raja1="";
            if(pop.isSelected())
            {
                pop1="Pop";
            }
            if(classical.isSelected())
            {
                classical1="Classical";
            }
            if(metal.isSelected())
            {
                metal1="Metal";
            }
            if(folk.isSelected())
            {
                folk1="Folk";
            }
            if(romantic.isSelected())
            {
                romantic1="Romantic";
            }
            if(contemp.isSelected())
            {
                contemp1="Contemporary";
            }
            if(rap.isSelected())
            {
                rap1="Rap";
            }
            if(brostep.isSelected())
            {
                brostep1="Brostep";
            }
            if(regional.isSelected())
            {
                regional1="Regional";
            }
            if(hiphop.isSelected())
            {
                hiphop1="Hiphop";
            }
            if(band.isSelected())
            {
                band1="Band";
            }
            if(rock.isSelected())
            {
                rock1="Rock";
            }
//            --------------------------------LANGUAGES---------------------------------------
            if(english.isSelected())
            {
                eng1="English";
            }if(hindi.isSelected())
            {
                hindi1="Hindi";
            }if(punjabi.isSelected())
            {
                punjabi1="Punjabi";
            }if(harayanvi.isSelected())
            {
                harayanvi1="Harayanvi";
            }if(bihari.isSelected())
            {
                bihari1="Bihari";
            }if(telugu.isSelected())
            {
                telugu1="Telugu";
            }if(marathi.isSelected())
            {
                marathi1="Marathi";
            }if(gujarati.isSelected())
            {
                guj1="Gujarati";
            }if(french.isSelected())
            {
                french1="French";
            }if(spanish.isSelected())
            {
                spanish1="Spanish";
            }if(rajasthani.isSelected())
            {
                raja1="Rajasthani";
            }if(tamil.isSelected())
            {
                tamil1="Tamil";
            }
            UserRequest ob=new UserRequest(name1, phn1, email1, dob1, state1, pop1, rap1, classical1, metal1, contemp1, folk1, romantic1, hiphop1, brostep1, regional1, band1, rock1, eng1, hindi1, telugu1, harayanvi1, bihari1, punjabi1, french1, spanish1, tamil1, marathi1, guj1, raja1);
            ob.myRequest();


        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }
}