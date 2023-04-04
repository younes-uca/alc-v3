package ma.learn.quiz.rest.paiment;

import ma.learn.quiz.bean.Etudiant;
import ma.learn.quiz.bean.GroupeEtude;
import ma.learn.quiz.bean.Inscription;
import ma.learn.quiz.bean.PackStudent;
import ma.learn.quiz.service.impl.EtudiantService;
import ma.learn.quiz.service.impl.GroupeEtudeService;
import ma.learn.quiz.service.impl.InscriptionService;
import ma.learn.quiz.service.impl.PackStudentService;
import org.apache.commons.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.*;


@RestController
@RequestMapping("/")
@CrossOrigin("*")
public class Payment {
    public String OID;
    private Map<String, String> paymentError = new HashMap<>();

    @PostMapping("back")
    public String backPayment(HttpServletRequest request) throws UnsupportedEncodingException, NoSuchAlgorithmException {

        String storeKey = "Kz@27021985";
        request.setCharacterEncoding("UTF-8");

        // create sorted map
        SortedMap<String, String> allRequestParams = new TreeMap<String, String>(new Comparator<String>() {
            public int compare(String str1, String str2) {
                str1 = str1.toUpperCase(Locale.US);
                str2 = str2.toUpperCase(Locale.US);
                return str1.compareTo(str2);
            }
        });
        // get all paramater map
        Map<String, String[]> parameterMap = request.getParameterMap();
        Set<String> requestParams = parameterMap.keySet();
        for (String requestParam : requestParams) {
            String[] allRequestParamValues = parameterMap.get(requestParam);
            if (allRequestParamValues != null && allRequestParamValues.length > 0) {
                String value = allRequestParamValues[0];
                allRequestParams.put(requestParam, value);
                if (requestParam.equals("oid")) {
                    this.OID = value;
                }
            }
        }
        // init hash value
        String hashval3 = "";
        for (String requestParam : allRequestParams.keySet()) {
            String lowerParam = requestParam.toLowerCase(Locale.US);
            if (!lowerParam.equals("encoding") && !lowerParam.equals("hash")) {
                String value = request.getParameter(URLDecoder.decode(requestParam, "UTF-8"))
                        .replace("\\", "\\\\")
                        .replaceAll("\\n", "")
                        .replace("|", "\\|");
                hashval3 = hashval3 + value + "|";
            }
        }
        storeKey = storeKey.replace("\\", "\\\\").replace("|", "\\|");
        hashval3 += storeKey;
        MessageDigest messageDigest = MessageDigest.getInstance("SHA-512");
        messageDigest.update(hashval3.getBytes("UTF-8"));
        String actualHash = new String(Base64.encodeBase64(messageDigest.digest()), "UTF-8");

        String retrievedHash = request.getParameter("HASH");
        String procReturnCode = request.getParameter("ProcReturnCode");
        System.out.println("------------ ACTUAL HASH ---------------------");
        System.out.println(actualHash);
        System.out.println("------------ RETRIEVED HASH ---------------------");
        System.out.println(retrievedHash);

        if (actualHash.equals(retrievedHash)) {
            System.out.println("HASH is successful");
            if (procReturnCode.equals("00") && this.validateInscription()) {
                System.out.println("ACTION=POSTAUTH");
                return "ACTION=POSTAUTH";
            } else {
                System.out.println("APPROVED");
                return "APPROVED";
            }
        } else {
            System.out.println("FAILURE");
            return "FAILURE";
        }
    }


    @PostMapping("ok")
    public ModelAndView okOrFailPayment(HttpServletRequest request) throws UnsupportedEncodingException, NoSuchAlgorithmException {
        String storeKey = "Kz@27021985";
        // create sorted map
        SortedMap<String, String> allRequestParams = new TreeMap<String, String>(new Comparator<String>() {
            public int compare(String str1, String str2) {
                str1 = str1.toUpperCase(Locale.US);
                str2 = str2.toUpperCase(Locale.US);
                return str1.compareTo(str2);
            }
        });
        // get all paramater map
        Map<String, String[]> parameterMap = request.getParameterMap();
        Set<String> requestParams = parameterMap.keySet();
        for (String requestParam : requestParams) {
            String[] allRequestParamValues = parameterMap.get(requestParam);
            if (allRequestParamValues != null && allRequestParamValues.length > 0) {
                String value = allRequestParamValues[0];
                allRequestParams.put(requestParam, value);
                if (requestParam.equals("oid")) {
                    this.OID = value;
                }
            }
        }
        // init hash value
        String hashval3 = "";
        for (String requestParam : allRequestParams.keySet()) {
            String lowerParam = requestParam.toLowerCase(Locale.US);
            if (!lowerParam.equals("encoding") && !lowerParam.equals("hash")) {
                String value = request.getParameter(URLDecoder.decode(requestParam, "UTF-8"))
                        .replace("\\", "\\\\")
                        .replaceAll("\\n", "")
                        .replace("|", "\\|");
                hashval3 = hashval3 + value + "|";
            }
        }
        storeKey = storeKey.replace("\\", "\\\\").replace("|", "\\|");
        hashval3 += storeKey;
        MessageDigest messageDigest = MessageDigest.getInstance("SHA-512");
        messageDigest.update(hashval3.getBytes("UTF-8"));
        String actualHash = new String(Base64.encodeBase64(messageDigest.digest()), "UTF-8");
        String retrievedHash = request.getParameter("HASH");
        if (actualHash.equals(retrievedHash)) {
            System.out.println("HASH is successful");
            if (this.validateInscription()) return new ModelAndView("redirect:" + okUrl);
            else return new ModelAndView("redirect:" + failUrl + '/' + OID);
        } else {
            this.paymentError.put(OID, "The digital signature is not valid");
            System.out.println("Security Alert. The digital signature is not valid");
            return new ModelAndView("redirect:" + failUrl + '/' + OID);
        }
    }


    @PostMapping("fail")
    public ModelAndView failPayment(HttpServletRequest request) throws UnsupportedEncodingException, NoSuchAlgorithmException {
        return new ModelAndView("redirect:" + failUrl);
    }

    private boolean validateInscription() {
        String[] ids = this.OID.split("-");
        long packId = Long.parseLong(ids[0]);
        long studentId = Long.parseLong(ids[1]);
        //verify existence of  pack
        PackStudent packStudent = packStudentService.findById(packId);
        if (packStudent == null) {
            this.paymentError.put(OID, "Pack not found!");
            return false;
        }

        //verify existence of student
        Etudiant etudiant = etudiantService.findById(studentId);
        if (etudiant == null) {
            this.paymentError.put(OID, "Student account not found!");
            return false;
        }

        System.out.println("Student ID: " + studentId);
        System.out.println("pack ID: " + packId);
        Inscription inscription = inscriptionService.findInscriptionByEtudiantId(studentId);
        if (inscription == null) {
            this.paymentError.put(OID, "Inscription not found!");
            return false;
        }
        if (inscription.getPackStudent() == null) return false;
        if (inscription.getPackStudent().getId() != packId) {
            this.paymentError.put(OID, "Price not found.");
            return false;
        }
        System.out.println(inscription);
        this.inscriptionService.validateInscription(inscription);
        // create group of student if student has individual pack
        if (!packStudent.isForGroupe()) {
            GroupeEtude groupeEtude = groupeEtudeService.findByNumberOfStudent(1L);
            this.inscriptionService.affecter(packStudent.getLevel(), groupeEtude, etudiant);
        }
        return true;
    }

    @GetMapping("pay-error-reason/{oid}")
    private String getReason(@PathVariable String oid) {
        if (this.paymentError.containsKey(oid)) {
            return paymentError.get(oid);
        }
        return null;
    }

    @Autowired
    private InscriptionService inscriptionService;
    @Autowired
    private GroupeEtudeService groupeEtudeService;
    @Autowired
    private EtudiantService etudiantService;
    @Autowired
    private PackStudentService packStudentService;

    @Value("${primary.link.for.payment.ok}")
    private String okUrl;
    @Value("${primary.link.for.payment.fail}")
    private String failUrl;
    @Value("${primary.link.for.engflexy}")
    private String engFlexy;
}
/*
lowerParam.equals("amount")
                    || lowerParam.equals("billtocity")
                    || lowerParam.equals("billtocountry")
                    || lowerParam.equals("billtopostalcode")
                    || lowerParam.equals("billtostateprov")
                    || lowerParam.equals("billtostreet1")
                    || lowerParam.equals("callbackurl")
                    || lowerParam.equals("clientid")
                    || lowerParam.equals("currency")
                    || lowerParam.equals("desc1")
                    || lowerParam.equals("engflexy")
                    || lowerParam.equals("failurl")
                    || lowerParam.equals("hashalgorithm")
                    || lowerParam.equals("itemnumber1")
                    || lowerParam.equals("lang")
                    || lowerParam.equals("oid")
                    || lowerParam.equals("okurl")
                    || lowerParam.equals("price1")
                    || lowerParam.equals("productcode1")
                    || lowerParam.equals("qty1")
                    || lowerParam.equals("shopurl")
                    || lowerParam.equals("storekey")
                    || lowerParam.equals("storetype")
                    || lowerParam.equals("total1")
                    || lowerParam.equals("trantype")

 */