package com.dataseed;

import com.entities.*;
import com.repositories.ComposanteRepository;
import com.repositories.CoursRepository;
import com.repositories.Filiere_langueRepository;
import com.repositories.UtilisateurRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataLoader implements CommandLineRunner {

    @Autowired
    UtilisateurRepository userRepository;
    @Autowired
    CoursRepository coursRepository;
    @Autowired
    ComposanteRepository compRepository;
    @Autowired
    Filiere_langueRepository flRepository;

    //lancé à chaque lancement du serveur
    @Override
    public void run(String ...args) throws Exception{
        loadCoursData();
        loadComposanteData();
        loadFiliereLangueData();
        loadJoinCoursFiliereData();
        loadUtilisateurData();
    }



    /**
     * Rajoute quatre cours dans la base de données si celle-ci est vide.
     */
    private void loadCoursData(){
        if(coursRepository.count() == 0){
            Cours anglais = new Cours();
            anglais.setIntitule("Anglais");
            coursRepository.save(anglais);

            Cours allemand = new Cours();
            allemand.setIntitule("Allemand");
            coursRepository.save(allemand);

            Cours japonais = new Cours();
            japonais.setIntitule("Japonais");
            coursRepository.save(japonais);

            Cours russe = new Cours();
            russe.setIntitule("Russe");
            coursRepository.save(russe);
        }
    }

    /**
     * Rajoute une composante dans la base de données si celle-ci est vide.
     */
    private void loadComposanteData(){
        if(compRepository.count() == 0){
            Composante comp1 = new Composante();
            comp1.setNomComposante("Une Composante");
            compRepository.save(comp1);
        }
    }

    /**
     * Rajoute deux filières langues dans la base de données si celle-ci est vide.
     */
    private void loadFiliereLangueData(){
        if(flRepository.count() == 0){
            Filiere_langue m1_info = new Filiere_langue();
            m1_info.setNom("M1 Informatique");
            m1_info.setCode("M1_Info");
            m1_info.setComposante(compRepository.getById(new Long(1)));
            flRepository.save(m1_info);

            Filiere_langue l3_info = new Filiere_langue();
            l3_info.setNom("L3 Informatique");
            l3_info.setCode("L3_Info");
            l3_info.setComposante(compRepository.getById(new Long(1)));
            flRepository.save(l3_info);

        }
    }

    private void loadJoinCoursFiliereData(){

    }

    /**
     * Rajoute trois utilisateurs (un Gestionnaire, un Reponsable et un Intervenant)
     * Dans la base de donnée du serveur si celle-ci est vide.
     */
    private void loadUtilisateurData(){
        if(userRepository.count() == 0){
            Responsable resp = new Responsable();
            resp.setLogin("Rodolphe");
            resp.setMdp("141ab274590fd72af5abbee9b64eabb4daae135844eaedc2634625d25c52bfa5");
            resp.setMail("rodolphe@gmail.com");
            resp.setNom("le Responsable");
            resp.setPrenom("Rodolphe");
            resp.setComposante(compRepository.getById(new Long(1)));
            userRepository.save(resp);

            Utilisateur gest = new Gestionnaire();
            gest.setLogin("Gauvain");
            gest.setNom("de Carmélide");
            gest.setPrenom("Gauvain");
            gest.setMail("gauvin.pedestres@kaamelot.bzh");
            gest.setMdp("141ab274590fd72af5abbee9b64eabb4daae135844eaedc2634625d25c52bfa5");
            userRepository.save(gest);

            Utilisateur inter = new Intervenant();
            inter.setLogin("Yvain");
            inter.setNom("de Loth");
            inter.setPrenom("Yvain");
            inter.setMail("yvain.pedestres@kaamelot.bzh");
            inter.setMdp("141ab274590fd72af5abbee9b64eabb4daae135844eaedc2634625d25c52bfa5");
            userRepository.save(inter);
        }
    }

}
