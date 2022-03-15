package com.dataseed;

import com.entities.*;
import com.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataLoader implements CommandLineRunner {

    @Autowired
    IntervenantRepository interRepository;
    @Autowired
    UtilisateurRepository userRepository;
    @Autowired
    CoursRepository coursRepository;
    @Autowired
    ComposanteRepository compRepository;
    @Autowired
    Filiere_langueRepository flRepository;
    @Autowired
    CreneauRepository creneauRepository;
    @Autowired
    SeanceFormationRepository seanceRepository;

    //lancé à chaque lancement du serveur
    @Override
    public void run(String ...args) throws Exception{
        loadCoursData();
        loadComposanteData();
        loadFiliereLangueData();
        loadJoinCoursFiliereData();
        loadUtilisateurData();

        loadCreneauData();
        loadSeanceData();

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
            Composante ufr_science = new Composante();
            ufr_science.setNomComposante("UFR Sciences");
            compRepository.save(ufr_science);
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

    private void loadCreneauData(){
        if (creneauRepository.count() == 0) {
            Creneau anglais_matin = new Creneau();
            anglais_matin.setCours(coursRepository.getById(new Long(1)));
            anglais_matin.setDate_heure("14-03-2022 8h");
            anglais_matin.setSalle("micro 2.4");
            anglais_matin.setDuree(120);
            anglais_matin.setType("TP");
            creneauRepository.save(anglais_matin);

            Creneau anglais_soir = new Creneau();
            anglais_soir.setCours(coursRepository.getById(new Long(1)));
            anglais_soir.setDate_heure("14-03-2022 15h30");
            anglais_soir.setSalle("micro 2.4");
            anglais_soir.setDuree(120);
            anglais_soir.setType("TD");
            creneauRepository.save(anglais_soir);
        }
    }

    private void loadSeanceData(){
        if (seanceRepository.count() == 0) {
            SeanceFormation seanceEffectue = new SeanceFormation();
            seanceEffectue.setCreneau(creneauRepository.getById(new Long(1)));
            seanceEffectue.setDureeEffective(115);
            Intervenant intervenantAnglais = interRepository.getById(new Long(3));
            seanceEffectue.setIntervenant(intervenantAnglais);
            seanceEffectue.setCommentaire("Le projecteur ne semble pas fonctionné.");
            seanceEffectue.setEstEffectue(true);

            seanceRepository.save(seanceEffectue);

            SeanceFormation seanceNonEffectue = new SeanceFormation();
            seanceNonEffectue.setCreneau(creneauRepository.getById(new Long(2)));
            seanceNonEffectue.setDureeEffective(0);
            seanceNonEffectue.setIntervenant(interRepository.getById(new Long(3)));
            seanceNonEffectue.setEstEffectue(false);

            seanceRepository.save(seanceNonEffectue);
        }
    }

}
