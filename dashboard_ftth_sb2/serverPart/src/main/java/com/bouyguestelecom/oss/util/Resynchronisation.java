package com.bouyguestelecom.oss.util;

import com.bouyguestelecom.oss.service.OltSlotPortPonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * Singleton permettant le gestion de la resynchronisation
 */

public class Resynchronisation {
    /**
     * Instance du singleton
     */
    private static final Resynchronisation instance = new Resynchronisation();


    /**
     * Booléen d'état de resynchronisation
     * true : FTTH_IG_FM est cours de resynchronisation
     * false : FTTH_IG_FM a terminé la resynchronisation
     */
    private boolean isResynch;

    /**
     * Date de la resinchronisation
     */
    private Date date;

    /**
     * Constructeur
     */
    private Resynchronisation(){
        this.isResynch = false;
    }

    /**
     * Getter de du Singleton
     * @return
     */
    public static Resynchronisation getInstance(){
        return instance;
    }

    // GETTERS ET SETTERS //
    public boolean isResynch() {
        return isResynch;
    }

    /**
     * Set l'état de la resynchronisation, la date
     * @param resynch boolean send by FTTH_IG_FM
     */
    public void setResynch(boolean resynch) {
        if(resynch){
            setDate(new Date());
        }else{
            setDate(null);
        }
        isResynch = resynch;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
