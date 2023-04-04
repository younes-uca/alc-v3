/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ma.learn.quiz.migration;

import ma.learn.quiz.bean.*;
import ma.learn.quiz.dao.*;
import ma.learn.quiz.filter.TypeQuiz;
import ma.learn.quiz.migration.constant.Constants;
import ma.learn.quiz.migration.util.DownloaderUtil;
import ma.learn.quiz.migration.util.FileUtil;
import ma.learn.quiz.migration.util.JsoupUtil;
import ma.learn.quiz.service.impl.*;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.Id;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

@Service
public class DataBaseMigration {
    private String ref;
    private Cours cour;

    public DataBaseMigration() {
    }


    @Autowired
    private ParcoursDao parcoursDao;
    @Autowired
    private CoursDao coursDao;
    @Autowired
    private CoursService coursService;
    @Autowired
    private SectionDao sectionDao;
    @Autowired
    private CentreDao centreDao;
    @Autowired
    private CategorieSectionDao categorieSectionDoa;
    @Autowired
    private SectionItemDao sectionItemDao;
    @Autowired
    private SuperCategorieSectionDao superCategorieSectionDao;
    private Long id;
    @Autowired
    private TypeDeQuestionDao typeDeQuestionDao;
    @Autowired
    private QuestionService questionService;
    @Autowired
    private ReponseService reponseService;
    @Autowired
    private QuizService quizService;
    @Autowired
    private HomeWorkQuestionDao homeWorkQuestionDao;
    @Autowired
    private HomeWorkQuestionService homeWorkQuestionService;
    @Autowired
    private HomeWorkDao homeWorkDao;
    @Autowired
    private HomeWorkQSTReponseDao homeWorkQSTReponseDao;
    @Autowired
    private TranslationEnAr translationEnAr;
    @Autowired
    private DriveApiService driveApiService;
    @Autowired
    private TypeHomeWorkService typeHomeWorkService;


    public String htmlimagetext() throws Exception {
        File imageDirRoot = FileUtil.mkdire(Constants.root1, "images", true);
        File directoryPathParcous = new File(Constants.root);
        String[] parcour = directoryPathParcous.list();
        for (int i = 0; i < parcour.length; i++) {
            Centre centre = centreDao.findCentreByLibelle("American Center 1");
            if (centre != null) {
                Parcours parcours = parcoursDao.findByLibelle(parcour[i]);
                if (parcours == null) {
                    parcours = new Parcours();
                    parcours.setCentre(centre);
                    parcours.setLibelle(parcour[i]);
                    parcours.setCode(parcour[i]);
                    parcours = parcoursDao.save(parcours);
                }
                String pathParcoursImages = imageDirRoot.getAbsolutePath() + "\\" + parcour[i] + "\\";
                String pathParcours = Constants.root + parcour[i] + "\\";
                FileUtil.mkdire(pathParcoursImages, "Lesson", true);

                // LESSON

                String pathLessonOrHomeWork = pathParcours + "Lesson";
                String pathLessonOrHomeWorkImage = pathParcoursImages + "Lesson";
                File directoryPathSection = new File(pathLessonOrHomeWork);
                String[] section = directoryPathSection.list();

                // HOMEWORK
                String pathHomwork = pathParcours + "HOMEWORK";
                String pathHomeWorkImage = pathParcoursImages + "HOMEWORK";
                File directoryPathHomework = new File(pathHomwork);
                String[] typeHomewrok = directoryPathHomework.list();

                /**
                 * Insert SECTION && QUIZ
                 */
                insertSections(parcour, i, pathLessonOrHomeWork, pathLessonOrHomeWorkImage, section);

                /**
                 * Insert HOMEWORK
                 */

                insertHomeWorks(parcours, pathHomwork, pathHomeWorkImage, typeHomewrok);


            }
        }
        System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
        System.out.println("Finish Tnx For waiting");
        System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
        return "process finish thanks for waiting";
    }

    private void insertHomeWorks(Parcours parcours, String pathHomwork, String pathHomeWorkImage, String[] typeHomewrok) {
        for (int j = 0; j < typeHomewrok.length; j++) {
            String pathHomWork = pathHomwork + "\\" + typeHomewrok[j];
            String pathHomWorkImage = pathHomeWorkImage + "\\" + typeHomewrok[j];
            if (new File(pathHomWork).exists()) {
                FileUtil.mkdire(pathHomeWorkImage, pathHomWorkImage, true);
                System.out.println("++++++++++++++++++++++++++++++");
                System.out.println("pathHomeWork ==> " + pathHomWork);
                System.out.println("pathImage ==>" + pathHomWorkImage);
                File homeWorkFile = new File(pathHomWork);
                System.out.println(homeWorkFile.getName());
                if (homeWorkFile.getName().equalsIgnoreCase("WATCH IT.txt")) {
                    System.out.println(homeWorkFile.getName());
                    this.getWatchItData(homeWorkFile, parcours);
                } else {
                    extractHtmlImageAndContentForHomeWork(parcours.getId(), homeWorkFile, parcours.getLibelle(), typeHomewrok[j], pathHomWork, pathHomWorkImage);
                }
            }
        }
    }

    private void insertSections(String[] parcour, int i, String pathLessonOrHomeWork, String pathLessonOrHomeWorkImage, String[] section) {
        for (int j = 0; j < section.length; j++) {
            System.out.println("  sectionName ::::: " + "Lesson" + " " + parcour[i] + " " + section[j]);
            String pathSection = pathLessonOrHomeWork + "\\" + section[j];
            String pathSectionImage = pathLessonOrHomeWorkImage + "\\" + section[j];
            if (new File(pathSection).exists()) {
                FileUtil.mkdire(pathSectionImage, pathLessonOrHomeWorkImage, true);
                System.out.println("pathSection ==> " + pathSection);
                System.out.println("pathImage ==>" + pathSectionImage);
                System.out.println("++++++++++++++++++++++++++++++");
                extractHtmlImageAndContent(parcour[i], section[j], pathSection, pathSectionImage);
            }
        }
    }

    private void getWatchItData(File file, Parcours parcours) {
        int index = 0;
        System.out.println(parcours.getId());
        List<Cours> coursList = this.coursDao.findByParcoursIdOrderByNumeroOrder(parcours.getId());
        try {
            System.out.println(Arrays.toString(file.list()));
            Scanner myReader = new Scanner(file);
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                if (data.contains("https://www.youtube")) {
                    TypeDeQuestion typeDeQuestion = typeDeQuestionDao.findByLibIgnoreCase("Watch and add new words");
                    TypeHomeWork typeHomeWork = typeHomeWorkService.findByLibelle("Watch it");
                    if (typeDeQuestion == null) {
                        typeDeQuestion = this.saveTypeQstHomeWork("Watch and add new words");
                    }
                    for (Cours cour : coursList
                    ) {
                        if (cour.getNumeroOrder() == index) {
                            this.cour = cour;
                        }
                    }
                    HomeWork homeWork = homeWorkDao.findByLibelleAndCoursId("Watch it", this.cour.getId());
                    if (homeWork == null) {
                        homeWork = new HomeWork();
                    }
                    homeWork.setLibelle("Watch it");
                    homeWork.setTypeHomeWork(typeHomeWork);
                    homeWork.setCours(this.cour);
                    int firstindex = data.indexOf("https");
                    String urlVideo = data.substring(firstindex);
                    homeWork.setUrlVideo(urlVideo);
                    System.out.println(urlVideo);
                    homeWork = homeWorkDao.save(homeWork);


                    index++;
                }
            }
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }


    public void extractHtmlImageAndContentForHomeWork(Long parcourId, File homeWorkFile, String parcours, String typeHomewrok, String pathHomWork, String pathHomWorkImage) {
        List<File> htmlFiles = FileUtil.findHtmlFiles(pathHomWork);
        htmlFiles.stream()
                .sorted((f1, f2) -> FileUtil.compare(f1, f2))
                .forEach(f -> {
                    try {
                        String imageSrc = JsoupUtil.getImageSrc(f);
                        String fileExtention = FileUtil.getExtension(imageSrc);
                        String imageNameDestination = gatImagePath(pathHomWork, pathHomWorkImage, f, imageSrc, fileExtention);


                        // -----------------------------------------   Home Work --------------------------------
                        String courLib = JsoupUtil.getElementContent(f, "span.js-lesson-type.link-dropdown_text-link");
                        Cours cours = coursDao.findCoursByLibelleAndParcoursId(courLib, parcourId);

                        if (homeWorkFile.getName().toUpperCase().contains("LET'S PRACTICE")) {
                            /**
                             * INSERT QUIZ
                             */
                            HomeWork homeWork = homeWorkDao.findByLibelleAndCoursId("Let's practice", cours.getId());
                            if (homeWork == null) {
                                homeWork = new HomeWork();
                                homeWork.setLibelle("Let's practice");
                                TypeHomeWork typeHomeWork = this.typeHomeWorkService.findByLibelle("Let's practice");
                                homeWork.setTypeHomeWork(typeHomeWork);
                                homeWork.setCours(cours);
                                homeWork = homeWorkDao.save(homeWork);
                            }
                            // check type of quiz
                            String typeQuiz = JsoupUtil.getElementContent(f, "p.title-progress");
                            System.out.println("TYPE DE QUIZ: " + typeQuiz);
                            if (typeQuiz.equalsIgnoreCase(TypeQuiz.TRANSLATE_THE_PHRASE)) {
                                insertTranslateThePhraseForHomeWork(homeWork, f);
                            } else if (typeQuiz.equalsIgnoreCase(TypeQuiz.CHOOSE_THE_CORRECT_ALTERNATIVE)) {
                                insertChooseAlternativeForHomeWork(homeWork, f);
                            } else if (typeQuiz.equalsIgnoreCase(TypeQuiz.WRITE_THE_CORRECT_FORM)) {
                                insertTheCorrectFormForHomeWork(homeWork, f);
                            } else if (typeQuiz.equalsIgnoreCase(TypeQuiz.CORRECT_THE_MISTAKE)) {
                                insertCorrectMistakeForHomeWork(homeWork, f);
                            }
                        } else {


                            HomeWork homeWork = homeWorkDao.findByLibelleAndCoursId(typeHomewrok, cours.getId());
                            if (homeWork == null) {
                                homeWork = new HomeWork();
                                homeWork = saveHomeWork(homeWork, typeHomewrok, cours);
                            }
                            // -----------------------------------------   Type de Question --------------------------------
                            String lib = JsoupUtil.getElementContent(f, "p.title-progress");
                            String lib1 = JsoupUtil.getElementContent(f, "p.title-list");
                            TypeDeQuestion typeDeQuestion;
                            System.out.println(lib1);
                            System.out.println(lib1.length());
                            System.out.println(lib);
                            System.out.println(lib.length());
                            if (lib.length() != 0) {
                                typeDeQuestion = typeDeQuestionDao.findByLibIgnoreCase(lib);
                                if (typeDeQuestion == null) {
                                    typeDeQuestion = this.saveTypeQstHomeWork(lib);
                                }

                                if (imageNameDestination.length() > 0) {
                                    String imgName = parcours + cours.getLibelle() + typeHomewrok + FileUtil.fileNameWithOutExt(f.getName()) + "." + fileExtention;
                                    String drivePath = this.addImgToGoogleDrive(parcours, cours.getLibelle(), typeHomewrok, imageNameDestination, imgName);
                                    homeWork.setUrlImage(drivePath);
                                    homeWork = homeWorkDao.save(homeWork);
                                }
                                System.out.println("====================File PATH =======================");
                                System.out.println(f.getName());


                                System.out.println("++++++++++++++++++++++ HOME WORK QUESTION  ++++++++++++++++++++++");
                                String homeWorkQstLibelle;
                                if (Objects.equals(typeDeQuestion.getLib(), "Write it up")) {
                                    homeWorkQstLibelle = JsoupUtil.getElementContent(f, "p.text-task-write-it-up");
                                } else {
                                    homeWorkQstLibelle = JsoupUtil.getElementContent(f, "div.wrapper-information > p");
                                    this.ref = JsoupUtil.getElementContent(f, "p.title-information > strong");
                                }

                                System.out.println(homeWorkQstLibelle);
                                System.out.println(this.ref);

                                if (homeWorkQstLibelle.length() <= 0) {
                                    return;
                                }
                                HomeWorkQuestion homeWorkQuestion = homeWorkQuestionDao.findHomeWorkQuestionByLibelleAndHomeWorkId(homeWorkQstLibelle, homeWork.getId());
                                if (homeWorkQuestion == null) {
                                    homeWorkQuestion = saveHomeWorkQuestion(homeWork, typeDeQuestion, 1, homeWorkQstLibelle);
                                }
                            } else if (lib1.length() != 0) {
                                if (lib1.equals("Study the following phrases"))
                                    lib1 = "Translate the phrase";
                                typeDeQuestion = typeDeQuestionDao.findByLibIgnoreCase(lib1);
                                if (typeDeQuestion == null) {
                                    typeDeQuestion = this.saveTypeQstHomeWork(lib1);
                                }
                                Elements elements = JsoupUtil.getElements(f, "div.word-list.is-open");
                                int index = 1;

                                for (Element element : elements
                                ) {
                                    for (Element child : element.children()
                                    ) {
                                        String homeWorkQstLibelle = child.select("p.word").text();
                                        if (homeWorkQstLibelle.length() <= 0) {
                                            return;
                                        }
                                        HomeWorkQuestion homeWorkQuestion = homeWorkQuestionDao.findHomeWorkQuestionByLibelleAndHomeWorkId(homeWorkQstLibelle, homeWork.getId());
                                        if (homeWorkQuestion == null) {
                                            homeWorkQuestion = saveHomeWorkQuestion(homeWork, typeDeQuestion, index, homeWorkQstLibelle);
                                        }
                                        HoweWorkQSTReponse homeWorkReponse = new HoweWorkQSTReponse();
                                        saveHomeWorkQstReponse(index, homeWorkQuestion, homeWorkReponse);
                                        index++;
                                    }
                                }
                            }
                        }
                    } catch (Exception ex) {
                        Logger.getLogger(DataBaseMigration.class.getName()).log(Level.SEVERE, null, ex);
                    }
                });
        System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
        System.out.println("FINISH TNX FOR WAITING");
        System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
    }

    private String addImgToGoogleDrive(String parcours, String libelle, String typeHomewrok, String imageNameDestination, String imgName) throws Exception {
        return this.driveApiService.createFile(parcours, libelle, typeHomewrok, imageNameDestination, imgName);
    }

    private String gatImagePath(String pathHomWork, String pathHomWorkImage, File f, String imageSrc, String fileExtention) throws IOException {

        String imageNameSource;
        if (!imageSrc.startsWith("https")) {
            imageNameSource = pathHomWork + "\\" + imageSrc;
        } else {
            String tmpFolderForDownladedImage = pathHomWork + "\\tmp\\" + FileUtil.fileNameWithOutExt(f.getName()) + "." + fileExtention;
            System.out.println("4444444444 DOWNLOADING tmpFolderForDownladedImage ==>>>" + tmpFolderForDownladedImage);
            FileUtil.mkdire(pathHomWork, "tmp", true);
            DownloaderUtil.exec(imageSrc, tmpFolderForDownladedImage, fileExtention);
            imageNameSource = tmpFolderForDownladedImage;
        }

        final String imageNameDestination = pathHomWorkImage + "\\" + FileUtil.fileNameWithOutExt(f.getName()) + "." + fileExtention;
        System.out.println("imageNameSource = " + imageNameSource + " && imageNameDestination " + imageNameDestination);
        return imageNameSource;
    }


    private void saveHomeWorkQstReponse(int index, HomeWorkQuestion homeWorkQuestion, HoweWorkQSTReponse homeWorkReponse) throws IOException {
        homeWorkReponse.setEtatReponse("true");
        homeWorkReponse.setHomeWorkQuestion(homeWorkQuestion);
        homeWorkReponse.setLib(this.translate(homeWorkQuestion.getLibelle()));
        homeWorkReponse.setNumero(index);
        homeWorkReponse = homeWorkQSTReponseDao.save(homeWorkReponse);
    }

    private HomeWorkQuestion saveHomeWorkQuestion(HomeWork homeWork, TypeDeQuestion typeDeQuestion, int index, String homeWorkQstLibelle) {
        HomeWorkQuestion homeWorkQuestion = new HomeWorkQuestion();
        if (Objects.equals(typeDeQuestion.getLib(), "Read and add new words")) {
            homeWorkQuestion.setRef(this.ref);
        }
        homeWorkQuestion.setTypeDeQuestion(typeDeQuestion);
        homeWorkQuestion.setLibelle(homeWorkQstLibelle);
        homeWorkQuestion.setPointReponseJuste(1);
        homeWorkQuestion.setPointReponsefausse(0);
        homeWorkQuestion.setHomeWork(homeWork);
        homeWorkQuestion.setNumero(index);
        System.out.println("**************************************");
        System.out.println(homeWorkQuestion.getLibelle());
        homeWorkQuestion = homeWorkQuestionDao.save(homeWorkQuestion);
        return homeWorkQuestion;
    }

    private HomeWork saveHomeWork(HomeWork homeWork, String typeHomewrok, Cours cours) {
        homeWork.setLibelle(typeHomewrok);
        homeWork.setCours(cours);
        TypeHomeWork t = this.typeHomeWorkService.findByLibelle(typeHomewrok);
        homeWork.setTypeHomeWork(t);
        homeWork = homeWorkDao.save(homeWork);
        return homeWork;
    }

    public TypeDeQuestion saveTypeQstHomeWork(String lib) {
        TypeDeQuestion t1 = new TypeDeQuestion();
        t1.setLib(lib);
        List<TypeDeQuestion> questionList = typeDeQuestionDao.findAll();
        int ref = questionList.size() + 1;
        t1.setRef("t" + ref);
        t1 = typeDeQuestionDao.save(t1);
        return t1;
    }

    public String translate(String word) throws IOException {
        System.out.println(word);
        String text = this.translationEnAr.TranslationResult(word);
        System.out.println("//////////////////////////////////////////");
        System.out.println(text);
        return text;
    }

    public void extractHtmlImageAndContent(String nameparcours, String categorieSection, String directoryName, String imagePath) {
        List<File> htmlFiles = FileUtil.findHtmlFiles(directoryName);
        htmlFiles.stream()
                .sorted((f1, f2) -> FileUtil.compare(f1, f2))
                .forEach(f -> {
                    try {
                        Parcours parcours = parcoursDao.findByLibelle(nameparcours);
                        if (parcours != null) {
                            String imageSrc = JsoupUtil.getImageSrc(f);
                            String fileExtention = FileUtil.getExtension(imageSrc);
                            Cours cours = coursDao.findCoursByLibelleAndParcoursId(JsoupUtil.getElementContent(f, "div.link-dropdown_link-dropdown"), parcours.getId());
                            if (cours == null) {
                                cours = new Cours();
                                cours.setParcours(parcours);
                                cours.setLibelle(JsoupUtil.getElementContent(f, "div.link-dropdown_link-dropdown"));
                                if (Objects.equals(cours.getLibelle(), "Intro lesson")) {
                                    cours.setNumeroOrder(0);
                                } else {
                                    String myTxt = cours.getLibelle().substring(0, 2).replaceAll("\\s", "");
                                    cours.setNumeroOrder(Integer.parseInt(myTxt));
                                }
                                cours = coursService.create(cours);
                                parcours.setNombreCours(parcours.getNombreCours() + 1);
                                parcoursDao.save(parcours);
                            }
                            int firstIndex = directoryName.lastIndexOf("Lesson");
                            String sectionName = directoryName.substring(firstIndex + 7);
                            if (sectionName.toUpperCase().contains("LET'S PRACTICE")) {
                                /**
                                 * INSERT QUIZ
                                 */
                                Section section = sectionDao.findSectionByLibelleAndCoursId("Let's practice", cours.getId());
                                if (section == null) {
                                    section = new Section();
                                    section.setLibelle("Let's practice");
                                    CategorieSection categorieSection2 = this.categorieSectionDoa.findCategorieSectionByLibelleIgnoreCase("Let's practice");
                                    section.setCategorieSection(categorieSection2);
                                    section.setCode(categorieSection2.getCode());
                                    section.setNumeroOrder(categorieSection2.getNumeroOrder());
                                    section.setCours(cours);
                                    sectionDao.save(section);
                                }
                                // check type of quiz
                                String typeQuiz = JsoupUtil.getElementContent(f, "p.title-progress");
                                System.out.println("TYPE DE QUIZ: " + typeQuiz);
                                if (typeQuiz.equalsIgnoreCase(TypeQuiz.TRANSLATE_THE_PHRASE)) {
                                    insertTranslateThePhrase(section, f);
                                } else if (typeQuiz.equalsIgnoreCase(TypeQuiz.CHOOSE_THE_CORRECT_ALTERNATIVE)) {
                                    insertChooseAlternative(section, f);
                                } else if (typeQuiz.equalsIgnoreCase(TypeQuiz.WRITE_THE_CORRECT_FORM)) {
                                    insertTheCorrectForm(section, f);
                                } else if (typeQuiz.equalsIgnoreCase(TypeQuiz.CORRECT_THE_MISTAKE)) {
                                    insertCorrectMistake(section, f);
                                }
                            } else if (sectionName.toUpperCase().contains("LIFE STORY")) {

                                Section section = sectionDao.findSectionByLibelleAndCoursId("Life Story", cours.getId());
                                if (section == null) {
                                    System.out.println("LIFE_STORY");
                                    section = new Section();
                                    section.setLibelle("Life Story");
                                    CategorieSection categorieSection1 = categorieSectionDoa.findCategorieSectionByLibelleIgnoreCase(section.getLibelle());
                                    section.setCategorieSection(categorieSection1);
                                    section.setNumeroOrder(categorieSection1.getNumeroOrder());
                                    section.setCours(cours);
                                    sectionDao.save(section);

                                    // check type of quiz
                                    String typeQstLib = JsoupUtil.getElementContent(f, "p.title-progress");
                                    TypeDeQuestion typeDeQuestion = this.typeDeQuestionDao.findByLibIgnoreCase(typeQstLib);
                                    if (typeDeQuestion != null) {
                                        if (typeDeQuestion.getLib().equalsIgnoreCase("Put words to gap")) {
                                            this.insertPutWords2Gap(f, section, typeDeQuestion);
                                        } else if (typeDeQuestion.getLib().equalsIgnoreCase("Put in order")) {
                                            this.insert_put_in_order(f, section, typeDeQuestion);
                                        } else if (typeDeQuestion.getLib().equalsIgnoreCase("True or false")) {
                                            insert_true_or_false(f, section, typeDeQuestion);
                                        }
                                    }
                                }
                            } else if (sectionName.toUpperCase().contains("VOCABULARY")) {
                                Section section = sectionDao.findSectionByLibelleAndCoursId("Vocabulary", cours.getId());
                                if (section == null) {
                                    section = new Section();
                                    section.setLibelle("Vocabulary");
                                    CategorieSection categorieSection1 = categorieSectionDoa.findCategorieSectionByLibelleIgnoreCase(section.getLibelle());
                                    section.setCategorieSection(categorieSection1);
                                    section.setCode(JsoupUtil.getElementContent(f, "p.title-progress"));
                                    section.setNumeroOrder(categorieSection1.getNumeroOrder());
                                    section.setCours(cours);
                                    section = sectionDao.save(section);
                                }
                                SectionItem sectionItem = new SectionItem();
                                sectionItem.setSection(section);
                                String word = JsoupUtil.getElementContent(f, "div.en-word");
                                sectionItem.setResponse(word);
                                sectionItem.setTranscription(JsoupUtil.getElementContent(f, "p.transcription"));
                                sectionItem.setTranslation(this.translate(word));
                                sectionItem.setExample(JsoupUtil.getElementContent(f, "div.wrapper-text-example"));
                                String definition = JsoupUtil.getElementContent(f, "div.container-example > p.text-example"); //

                                Elements elements = JsoupUtil.getElements(f, "div.wrapper-synonyms > div.audio-example");
                                List<String> synonyms = new ArrayList<>();
                                for (Element element : elements
                                ) {
                                    for (Element child : element.children()
                                    ) {
                                        String synonym = child.select("p.text-example").text();
                                        synonyms.add(synonym);
                                    }
                                }
                                sectionItem.setSynonyms(synonyms);
                                sectionItem.setExplanation(definition);
                                String imageNameSource = null;
                                if (!imageSrc.startsWith("https")) {
                                    imageNameSource = directoryName + "\\" + imageSrc;
                                } else {
                                    String tmpFolderForDownladedImage = directoryName + "\\tmp\\" + FileUtil.fileNameWithOutExt(f.getName()) + "." + fileExtention;
                                    FileUtil.mkdire(directoryName, "tmp", true);
                                    DownloaderUtil.exec(imageSrc, tmpFolderForDownladedImage, fileExtention);
                                    imageNameSource = tmpFolderForDownladedImage;
                                }
                                final String imageNameDestination = imagePath + "\\" + FileUtil.fileNameWithOutExt(f.getName()) + "." + fileExtention;
                                FileUtil.copyFile(imageNameSource, imageNameDestination);
                                String imgName = parcours.getLibelle() + cours.getLibelle() + section.getCategorieSection().getLibelle() + FileUtil.fileNameWithOutExt(f.getName()) + "." + fileExtention;

                                String drivePath = this.addImgToGoogleDrive(parcours.getLibelle(), cours.getLibelle(), sectionItem.getExample(), imageNameDestination, imgName);
                                System.out.println("_____________________________SECTION ITEM__________________________________________");
                                System.out.println(drivePath);
                                sectionItem.setImageUrl(drivePath);
                                System.out.println(sectionItem);
                                System.out.println("___________________________________________________________________________________");
                                sectionItemDao.save(sectionItem);
                            } else {
                                Section section = sectionDao.findSectionByLibelleAndCoursId(JsoupUtil.getElementContent(f, "p.title-progress"), cours.getId());
                                if (section == null) {
                                    section = new Section();
                                    section.setLibelle(JsoupUtil.getElementContent(f, "p.title-progress"));
                                    System.out.println("SECTION NAME: " + sectionName);
                                    CategorieSection categorieSection1 = categorieSectionDoa.findCategorieSectionByLibelleIgnoreCase(sectionName);
                                    section.setCategorieSection(categorieSection1);
                                    section.setCode(JsoupUtil.getElementContent(f, "p.title-progress"));
                                    section.setNumeroOrder(categorieSection1.getNumeroOrder());
                                    if (sectionName.equalsIgnoreCase("Get to know")) {
                                        Elements elements = JsoupUtil.getElements(f, "div.play-audio");
                                        String text = " ";
                                        for (Element element : elements
                                        ) {
                                            System.out.println(element);
                                            for (Element child : element.children()
                                            ) {
                                                text = text.concat(child.select("span.audio").text() + " ");
                                            }
                                        }
                                        section.setContenu(text);
                                    } else {
                                        section.setContenu(JsoupUtil.getElementContentLesson(f, "div.wrapper-information"));
                                    }
                                    section.setCours(cours);
                                    sectionDao.save(section);
                                }


                                String imageNameSource = null;
                                if (!imageSrc.startsWith("https")) {
                                    imageNameSource = directoryName + "\\" + imageSrc;
                                } else {
                                    String tmpFolderForDownladedImage = directoryName + "\\tmp\\" + FileUtil.fileNameWithOutExt(f.getName()) + "." + fileExtention;
                                    FileUtil.mkdire(directoryName, "tmp", true);
                                    DownloaderUtil.exec(imageSrc, tmpFolderForDownladedImage, fileExtention);
                                    imageNameSource = tmpFolderForDownladedImage;
                                }
                                final String imageNameDestination = imagePath + "\\" + FileUtil.fileNameWithOutExt(f.getName()) + "." + fileExtention;
                                FileUtil.copyFile(imageNameSource, imageNameDestination);
                                String imgName = parcours.getLibelle() + cours.getLibelle() + section.getCategorieSection().getLibelle() + FileUtil.fileNameWithOutExt(f.getName()) + "." + fileExtention;

                                String drivePath = this.addImgToGoogleDrive(parcours.getLibelle(), cours.getLibelle(), section.getCategorieSection().getLibelle(), imageNameDestination, imgName);
                                System.out.println("_______________________________________________________________________");
                                System.out.println(drivePath);
                                section.setUrlImage(drivePath);
                                sectionDao.save(section);
                                System.out.println("_______________________________________________________________________");
                            }


                        }


                    } catch (Exception ex) {
                        Logger.getLogger(DataBaseMigration.class.getName()).log(Level.SEVERE, null, ex);
                    }
                });
    }

    private void insert_true_or_false(File f, Section section, TypeDeQuestion typeDeQuestion) throws Exception {
        Elements elements = JsoupUtil.getElements(f, "div.true-false-item");
        int index = 1;
        System.out.println("___________________________TRUE OR FALSE______________________________");
        for (Element element : elements
        ) {
            System.out.println(element);
            for (Element child : element.children()
            ) {
                Question question = new Question();
                Reponse reponse = new Reponse();
                Reponse reponse2 = new Reponse();
                List<Reponse> reponseList = new ArrayList<>();
                String lib = child.select("div.text-true-false-wrap").text();
                if (lib != null && lib.length() > 3) {
                    System.out.println("LIBELLE = " + lib);
                    System.out.println("INDEX = " + index);
                    //create first reponse
                    reponse.setNumero(1L);
                    reponse.setLib("true");
                    reponse.setEtatReponse("true");
                    reponseList.add(reponse);

                    //create second reponse

                    reponse2.setNumero(2L);
                    reponse2.setLib("false");
                    reponse2.setEtatReponse("false");
                    reponseList.add(reponse2);

                    // create question

                    question.setTypeDeQuestion(typeDeQuestion);
                    question.setNumero((long) index);
                    question.setPointReponseJuste(1);
                    question.setReponses(reponseList);
                    question.setLibelle(lib);
                    this.saveQuiz(section, question);
                }

            }
            index++;
        }
        System.out.println("___________________________TRUE OR FALSE______________________________");
    }

    private void insert_put_in_order(File f, Section section, TypeDeQuestion typeDeQuestion) throws Exception {
        Elements elements = JsoupUtil.getElements(f, "div.put-in-order-list");
        String fullText = "";
        int index = 1;
        for (Element element : elements
        ) {
            for (Element child : element.children()
            ) {
                String reponseLib = index + " " + child.select("div.put-in-order-wrap").text();
                fullText = fullText.concat(reponseLib);
                index++;
            }
        }
        System.out.println("___________________________INSERT PUT IN ORDER_______________________________");
        System.out.println(fullText);
        System.out.println("___________________________INSERT PUT IN ORDER_______________________________");
        Question question = new Question();
        question.setTypeDeQuestion(typeDeQuestion);
        question.setNumero(1L);
        question.setLibelle(fullText);
        question.setPointReponseJuste(1);
        this.saveQuiz(section, question);
    }

    private void insertPutWords2Gap(File f, Section section, TypeDeQuestion typeDeQuestion) throws Exception {
        String fullText = JsoupUtil.getElementContent(f, "div.text-life-story.js-words-text-drop"); //
        Elements elements = JsoupUtil.getElements(f, "div.js-drop-words.js-words-list");
        for (Element element : elements
        ) {
            for (Element child : element.children()
            ) {
                String reponseLib = child.select("div.nest.is-used").text();
                fullText = fullText.replaceFirst(reponseLib, " @" + reponseLib + "@ ");
            }
        }
        Question question = new Question();
        question.setTypeDeQuestion(typeDeQuestionDao.findByRef("t13"));
        question.setNumero(1L);
        question.setPointReponseJuste(1);
        question.setLibelle(fullText);
        this.saveQuiz(section, question);
    }


    private SuperCategorieSection initSuperCategorie(File f) throws IOException {
        SuperCategorieSection superCategorieSection;
        superCategorieSection = new SuperCategorieSection();
        superCategorieSection.setCode(JsoupUtil.getElementContent(f, "p.lessons-list_title-additional-list"));
        superCategorieSection.setLibelle(JsoupUtil.getElementContent(f, "p.lessons-list_title-additional-list"));
        superCategorieSectionDao.save(superCategorieSection);
        return superCategorieSection;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Id
    public Long getId() {
        return id;
    }

    private void insertChooseAlternative(Section section, File f) throws Exception {
        TypeDeQuestion typeDeQuestion = this.typeDeQuestionDao.findByRef("t1");
        List<Reponse> listReponse = new ArrayList<>();
        //QUESTION
        Question question = new Question();
        String fullLibQst = JsoupUtil.getElementContent(f, "p.text-task");
        String correctAnswer = JsoupUtil.getElementContent(f, "span.right-text");

        String numQst = JsoupUtil.getElementContent(f, "p.count-progress");
        Elements elements = JsoupUtil.getElements(f, "div.answer-training");
        for (Element element : elements
        ) {
            for (Element child : element.children()
            ) {
                String reponseLib = child.select("p.text-variant").text();
                String numero = child.select("div.hot-key-training.b-hot-key").text();
                if (reponseLib.length() <= 0) {
                    return;
                }
                Reponse reponse = new Reponse();
                reponse.setNumero(Long.valueOf(numero));
                reponse.setLib(reponseLib);
                if (!reponseLib.equals(correctAnswer)) {
                    reponse.setEtatReponse("false");
                } else {
                    reponse.setEtatReponse("true");
                }
                listReponse.add(reponse);
            }
        }
        String strRepList = JsoupUtil.getElementContent(f, "span.empty-word");
        String libQst = fullLibQst.replace(strRepList, ".....");
        question.setLibelle(libQst);
        question.setPointReponsefausse(0);
        question.setPointReponseJuste(1);
        question.setTypeDeQuestion(typeDeQuestion);
        System.out.println("TYPE QST" + question.getTypeDeQuestion().getRef());
        question.setNumero(Long.valueOf(numQst.substring(0, 1)));
        question.setReponses(listReponse);
        saveQuiz(section, question);
    }


    private void insertTheCorrectForm(Section section, File f) throws Exception {
        TypeDeQuestion typeDeQuestion = this.typeDeQuestionDao.findByRef("t6");
        List<Reponse> listReponse = new ArrayList<>();
        //QUESTION
        Question question = new Question();
        String fullLibQst = JsoupUtil.getElementContent(f, "div.text-task");
        System.out.println("LIB: " + fullLibQst);
        String correctAnswer = JsoupUtil.getElementContent(f, "div.-center-tooltip.-answer-tooltip");
        System.out.println("CORRECT ANSWER: " + correctAnswer);

        String falseAnswer = "";
        String numQst = JsoupUtil.getElementContent(f, "p.count-progress");
        Elements elements = JsoupUtil.getElements(f, "div.text-input-wrap");
        System.out.println("ELEMENTS: " + elements);

        for (Element element : elements
        ) {
            for (Element child : element.children()
            ) {
                String reponseLib = child.select("div.text-input").text();

                if (reponseLib.length() > 1) {
                    if (!reponseLib.equalsIgnoreCase(correctAnswer)) {
                        System.out.println("REPONSELIB1: " + reponseLib);
                        falseAnswer = reponseLib;
                    } else {
                        System.out.println("REPONSELIB2: " + reponseLib);
                        Reponse reponse = new Reponse();
                        reponse.setNumero(1L);
                        reponse.setLib(correctAnswer);
                        reponse.setEtatReponse("true");
                        listReponse.add(reponse);
                    }
                }
            }
        }
        String strRepList = JsoupUtil.getElementContent(f, "div.input-wrap.js-wrap-input.is-incorrect");
        System.out.println("STRREPLIST: " + strRepList);
        String libQst = fullLibQst.replace(strRepList, "@" + falseAnswer + "@");
        System.out.println("LIBQST: " + libQst);
        String libQst2 = libQst.replace("Space", " ");
        System.out.println("LIBQST2: " + libQst2);

        question.setLibelle(libQst2);
        question.setPointReponsefausse(0);
        question.setPointReponseJuste(1);
        question.setTypeDeQuestion(typeDeQuestion);
        System.out.println("TYPE QST" + question.getTypeDeQuestion().getRef());
        question.setNumero(Long.valueOf(numQst.substring(0, 1)));
        question.setReponses(listReponse);
        saveQuiz(section, question);
    }

    private void insertTranslateThePhrase(Section section, File f) throws IOException {
        TypeDeQuestion typeDeQuestion = this.typeDeQuestionDao.findByRef("t3");
        System.out.println(typeDeQuestion.getRef());
        //QUESTION
        Question question = new Question();
        String libQst = JsoupUtil.getElementContent(f, "div.answer-task");
        String numQst = JsoupUtil.getElementContent(f, "p.count-progress");
        question.setLibelle(this.translate(libQst));
        question.setPointReponsefausse(0);
        question.setPointReponseJuste(1);
        question.setNumero(Long.valueOf(numQst.substring(0, 1)));
        question.setTypeDeQuestion(typeDeQuestion);
        System.out.println("TYPE QST" + question.getTypeDeQuestion().getRef());
        // REPONSE
        Reponse reponse = new Reponse();
        reponse.setEtatReponse("true");
        reponse.setLib(libQst);
        reponse.setNumero(1L);
        List<Reponse> reponseList = new ArrayList<>();
        reponseList.add(reponse);
        question.setReponses(reponseList);
        //QUIZ
        saveQuiz(section, question);
    }

    private void insertTranslateThePhraseForHomeWork(HomeWork homeWork, File f) throws IOException {
        TypeDeQuestion typeDeQuestion = this.typeDeQuestionDao.findByRef("t3");
        System.out.println(typeDeQuestion.getRef());
        //QUESTION
        HomeWorkQuestion question = new HomeWorkQuestion();
        String libQst = JsoupUtil.getElementContent(f, "div.answer-task");
        String numQst = JsoupUtil.getElementContent(f, "p.count-progress");
        question.setLibelle(this.translate(libQst));
        question.setPointReponsefausse(0);
        question.setPointReponseJuste(1);
        question.setNumero(Integer.parseInt(numQst.substring(0, 1)));
        question.setTypeDeQuestion(typeDeQuestion);
        System.out.println("TYPE QST" + question.getTypeDeQuestion().getRef());
        // REPONSE
        HoweWorkQSTReponse reponse = new HoweWorkQSTReponse();
        reponse.setEtatReponse("true");
        reponse.setLib(libQst);
        reponse.setNumero(1);
        List<HoweWorkQSTReponse> reponseList = new ArrayList<>();
        reponseList.add(reponse);
        question.setReponses(reponseList);
        //QUIZ
        homeWorkQuestionService.addQstHomeWork(homeWork, question);
    }

    private void insertChooseAlternativeForHomeWork(HomeWork homeWork, File f) throws Exception {
        TypeDeQuestion typeDeQuestion = this.typeDeQuestionDao.findByRef("t1");
        List<HoweWorkQSTReponse> listReponse = new ArrayList<>();
        //QUESTION
        HomeWorkQuestion question = new HomeWorkQuestion();
        String fullLibQst = JsoupUtil.getElementContent(f, "p.text-task");
        String correctAnswer = JsoupUtil.getElementContent(f, "span.right-text");

        String numQst = JsoupUtil.getElementContent(f, "p.count-progress");
        Elements elements = JsoupUtil.getElements(f, "div.answer-training");
        for (Element element : elements
        ) {
            for (Element child : element.children()
            ) {
                String reponseLib = child.select("p.text-variant").text();
                String numero = child.select("div.hot-key-training.b-hot-key").text();
                if (reponseLib.length() <= 0) {
                    return;
                }
                HoweWorkQSTReponse reponse = new HoweWorkQSTReponse();
                reponse.setNumero(Integer.parseInt(numero));
                reponse.setLib(reponseLib);
                if (!reponseLib.equals(correctAnswer)) {
                    reponse.setEtatReponse("false");
                } else {
                    reponse.setEtatReponse("true");
                }
                listReponse.add(reponse);
            }
        }
        String strRepList = JsoupUtil.getElementContent(f, "span.empty-word");
        String libQst = fullLibQst.replace(strRepList, ".....");
        question.setLibelle(libQst);
        question.setPointReponsefausse(0);
        question.setPointReponseJuste(1);
        question.setTypeDeQuestion(typeDeQuestion);
        System.out.println("TYPE QST" + question.getTypeDeQuestion().getRef());
        question.setNumero(Integer.parseInt(numQst.substring(0, 1)));
        question.setReponses(listReponse);
        homeWorkQuestionService.addQstHomeWork(homeWork, question);
    }

    private void insertTheCorrectFormForHomeWork(HomeWork homeWork, File f) throws Exception {
        TypeDeQuestion typeDeQuestion = this.typeDeQuestionDao.findByRef("t6");
        List<HoweWorkQSTReponse> listReponse = new ArrayList<>();
        //QUESTION
        HomeWorkQuestion question = new HomeWorkQuestion();
        String fullLibQst = JsoupUtil.getElementContent(f, "div.text-task");
        System.out.println("LIB: " + fullLibQst);
        String correctAnswer = JsoupUtil.getElementContent(f, "div.-center-tooltip.-answer-tooltip");
        System.out.println("CORRECT ANSWER: " + correctAnswer);

        String falseAnswer = "";
        String numQst = JsoupUtil.getElementContent(f, "p.count-progress");
        Elements elements = JsoupUtil.getElements(f, "div.text-input-wrap");
        System.out.println("ELEMENTS: " + elements);

        for (Element element : elements
        ) {
            for (Element child : element.children()
            ) {
                String reponseLib = child.select("div.text-input").text();

                if (reponseLib.length() > 1) {
                    if (!reponseLib.equalsIgnoreCase(correctAnswer)) {
                        System.out.println("REPONSELIB1: " + reponseLib);
                        falseAnswer = reponseLib;
                    } else {
                        System.out.println("REPONSELIB2: " + reponseLib);
                        HoweWorkQSTReponse reponse = new HoweWorkQSTReponse();
                        reponse.setNumero(1);
                        reponse.setLib(correctAnswer);
                        reponse.setEtatReponse("true");
                        listReponse.add(reponse);
                    }
                }
            }
        }
        String strRepList = JsoupUtil.getElementContent(f, "div.input-wrap.js-wrap-input.is-incorrect");
        System.out.println("STRREPLIST: " + strRepList);
        String libQst = fullLibQst.replace(strRepList, "@" + falseAnswer + "@");
        System.out.println("LIBQST: " + libQst);
        String libQst2 = libQst.replace("Space", " ");
        System.out.println("LIBQST2: " + libQst2);

        question.setLibelle(libQst2);
        question.setPointReponsefausse(0);
        question.setPointReponseJuste(1);
        question.setTypeDeQuestion(typeDeQuestion);
        System.out.println("TYPE QST" + question.getTypeDeQuestion().getRef());
        question.setNumero(Integer.parseInt(numQst.substring(0, 1)));
        question.setReponses(listReponse);
        homeWorkQuestionService.addQstHomeWork(homeWork, question);
    }

    private void insertCorrectMistakeForHomeWork(HomeWork homeWork, File f) throws IOException {
        TypeDeQuestion typeDeQuestion = this.typeDeQuestionDao.findByRef("t4");
        System.out.println(typeDeQuestion.getRef());
        //QUESTION
        HomeWorkQuestion question = new HomeWorkQuestion();
        String fullLibQst = JsoupUtil.getElementContent(f, "div.text-task-content");
        System.out.println("FULLLIBQST: " + fullLibQst);

        String fullLibAnswers = JsoupUtil.getElementContent(f, "div.input-wrap");
        System.out.println("FULLLIBANSWERS: " + fullLibAnswers);

        String falseInswer = JsoupUtil.getElementContent(f, "span.answer-grammar-mistake.is-incorrect");
        System.out.println("FALSEINSWER: " + falseInswer);

        String trueInswer = JsoupUtil.getElementContent(f, "div.b-tooltip.-answer-tooltip");
        System.out.println("TRUEINSWER: " + trueInswer);

        String libQst = fullLibQst.replace(fullLibAnswers, "@" + falseInswer + "@");
        System.out.println("LIBQST: " + libQst);

        String numQst = JsoupUtil.getElementContent(f, "p.count-progress");
        question.setLibelle(libQst);
        question.setPointReponsefausse(0);
        question.setPointReponseJuste(1);
        question.setNumero(Integer.parseInt(numQst.substring(0, 1)));
        question.setTypeDeQuestion(typeDeQuestion);
        System.out.println("TYPE QST" + question.getTypeDeQuestion().getRef());
        // REPONSE
        HoweWorkQSTReponse reponse = new HoweWorkQSTReponse();
        reponse.setEtatReponse("true");
        reponse.setLib(trueInswer);
        reponse.setNumero(1);
        List<HoweWorkQSTReponse> reponseList = new ArrayList<>();
        reponseList.add(reponse);
        question.setReponses(reponseList);
        //QUIZ
        homeWorkQuestionService.addQstHomeWork(homeWork, question);
    }

    private void insertCorrectMistake(Section section, File f) throws IOException {
        TypeDeQuestion typeDeQuestion = this.typeDeQuestionDao.findByRef("t4");
        System.out.println(typeDeQuestion.getRef());
        //QUESTION
        Question question = new Question();
        String fullLibQst = JsoupUtil.getElementContent(f, "div.text-task-content");
        System.out.println("FULLLIBQST: " + fullLibQst);

        String fullLibAnswers = JsoupUtil.getElementContent(f, "div.input-wrap");
        System.out.println("FULLLIBANSWERS: " + fullLibAnswers);

        String falseInswer = JsoupUtil.getElementContent(f, "span.answer-grammar-mistake.is-incorrect");
        System.out.println("FALSEINSWER: " + falseInswer);

        String trueInswer = JsoupUtil.getElementContent(f, "div.b-tooltip.-answer-tooltip");
        System.out.println("TRUEINSWER: " + trueInswer);

        String libQst = fullLibQst.replace(fullLibAnswers, "@" + falseInswer + "@");
        System.out.println("LIBQST: " + libQst);

        String numQst = JsoupUtil.getElementContent(f, "p.count-progress");
        question.setLibelle(libQst);
        question.setPointReponsefausse(0);
        question.setPointReponseJuste(1);
        question.setNumero(Long.valueOf(numQst.substring(0, 1)));
        question.setTypeDeQuestion(typeDeQuestion);
        System.out.println("TYPE QST" + question.getTypeDeQuestion().getRef());
        // REPONSE
        Reponse reponse = new Reponse();
        reponse.setEtatReponse("true");
        reponse.setLib(trueInswer);
        reponse.setNumero(1L);
        List<Reponse> reponseList = new ArrayList<>();
        reponseList.add(reponse);
        question.setReponses(reponseList);
        //QUIZ
        saveQuiz(section, question);
    }


    private void saveQuiz(Section section, Question question) {
        ZoneId defaultZoneId = ZoneId.systemDefault();
        LocalDate localDate = LocalDate.now();
        Date date = Date.from(localDate.atStartOfDay(defaultZoneId).toInstant());
        Quiz quiz = this.quizService.findBySectionId(section.getId());
        List<Question> questionList = new ArrayList<>();
        if (quiz == null) {
            quiz = new Quiz();
            quiz.setDateDebut(date);
            quiz.setLib(section.getLibelle());
            quiz.setSection(section);
            quiz.setRef("quiz-" + section.getId());
            questionList.add(question);
            quiz.setQuestions(questionList);
            this.quizService.saveAll(quiz);
        } else {
            questionList.add(question);
            quiz.setQuestions(questionList);
            for (Question q : quiz.getQuestions()
            ) {
                System.out.println(q.getId());
                System.out.println(q.getReponses().size());
                System.out.println(q.getTypeDeQuestion().getRef());
                System.out.println(q.getLibelle());
                System.out.println(q.getNumero());
            }
            this.quizService.updateAll(quiz);
        }
    }


    @Autowired
    private TypeDeQuestionService typeDeQuestionService;
}
