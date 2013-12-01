package javasrcs;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import twitter4j.Status;

/**
 *
 * @author user
 */
//@WebServlet(urlPatterns = {"/Proses"})
public class Proses extends HttpServlet {

    /**
     * Processes requests for both HTTP
     * <code>GET</code> and
     * <code>POST</code> methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        try {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet Proses</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet Proses at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        } finally {            
            out.close();
        }
    }
    protected void doGet(HttpServletRequest req, HttpServletResponse res)throws ServletException, IOException 
    {   
        PrintWriter out = res.getWriter();
        String [] pos, neg;
        String keyword = req.getParameter("tweetkeyword");
        String Positif = req.getParameter("positivesentiments");
        Positif = Positif.trim();
        pos = Positif.split(";");
        String Negatif = req.getParameter("negativesentiments");
        Negatif = Negatif.trim();
        neg = Negatif.split(";");
        String algotype = req.getParameter("algotype");
        List<Status> Temp1;
        ArrayList<String> Temp = new ArrayList<String>();
        ArrayList<String> TempPos = new ArrayList<String>();
        ArrayList<String> TempNeg = new ArrayList<String>();
        ArrayList<String> TempNetral = new ArrayList<String>();
        int sumPos = 0, sumNeg = 0;
        TweetSearch TS  = new TweetSearch();
        Temp1 = TS.search(keyword);
        Temp = TS.getTweetTextList(Temp1);
        
        if(algotype.equals("bm")){
            for(int i = 0; i < Temp.size(); i++){
                sumNeg = 0;
                sumPos = 0;
                for(int j =0; j < pos.length; j++){
                    BoyerMoore boyermoore1 = new BoyerMoore(pos[j]);
                    int offset1 = boyermoore1.search(Temp.get(i));
                    if(offset1 != 0){
                        sumPos++;
                    }
                }
                for(int j =0; j < neg.length; j++){
                    BoyerMoore boyermoore1 = new BoyerMoore(neg[j]);
                    int offset1 = boyermoore1.search(Temp.get(i));
                    if(offset1 != 0){
                        sumNeg++;
                    }
                }
                if(sumPos > sumNeg){
                    TempPos.add(Temp.get(i));
                }else if(sumPos < sumNeg){
                    TempNeg.add(Temp.get(i));
                }else{
                    TempNetral.add(Temp.get(i));
                }
            }
            out.println("<html>");
            out.println("<head>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h2><center> Analisis </center></h2>");
            out.println("<h3> Hasil Pencarian Dari "+ keyword +": </h3>");
            out.println("<ol>");
            for(int i = 0; i < Temp.size(); i++){
                out.println("<li>");
                out.println(Temp.get(i));
                out.println("</li>");
            }
            out.println("</ol>");
            out.println("<h3> Tweet Positif : </h3>");
            out.println("<ol>");
            for(int i = 0; i < TempPos.size(); i++){
                out.println("<li>");
                out.println(TempPos.get(i));
                out.println("</li>");
            }
            out.println("</ol>");
            out.println("<h3> Tweet Negatif : </h3>");
            out.println("<ol>");
            for(int i = 0; i < TempNeg.size(); i++){
                out.println("<li>");
                out.println(TempNeg.get(i));
                out.println("</li>");
            }
            out.println("</ol>");
            out.println("<h3> Tweet Netral : </h3>");
            out.println("<ol>");
            for(int i = 0; i < TempNetral.size(); i++){
                out.println("<li>");
                out.println(TempNetral.get(i));
                out.println("</li>");
            }
            out.println("</ol>");
            out.println("</body>");
            out.println("</html>");
            //nampilin halaman positif dan negatif dan netral
        }else if(algotype.equals("kmp")){
            for(int i = 0; i < Temp.size(); i++){
                sumNeg = 0;
                sumPos = 0;
                for(int j =0; j < pos.length; j++){
                    KMP kmp = new KMP(Temp.get(i), pos[j]);
                    int offset1 =kmp.kmpMatch(Temp.get(i), pos[j]);
                    if(offset1 != -1){
                        sumPos++;
                    }
                }
                for(int j =0; j < neg.length; j++){
                    KMP kmp = new KMP(Temp.get(i), neg[j]);
                    int offset1 =kmp.kmpMatch(Temp.get(i), neg[j]);
                    if(offset1 != -1){
                        sumNeg++;
                    }
                }
                if(sumPos > sumNeg){
                    TempPos.add(Temp.get(i));
                }else if(sumPos < sumNeg){
                    TempNeg.add(Temp.get(i));
                }else{
                    TempNetral.add(Temp.get(i));
                }
            }
            out.println("<html>");
            out.println("<head>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h2><center> Analisis </center></h2>");
            out.println("<h3> Hasil Pencarian Dari "+ keyword +": </h3>");
            out.println("<ol>");
            for(int i = 0; i < Temp.size(); i++){
                out.println("<li>");
                out.println(Temp.get(i));
                out.println("</li>");
            }
            out.println("</ol>");
            out.println("<h3> Tweet Positif : </h3>");
            out.println("<ol>");
            for(int i = 0; i < TempPos.size(); i++){
                out.println("<li>");
                out.println(TempPos.get(i));
                out.println("</li>");
            }
            out.println("</ol>");
            out.println("<h3> Tweet Negatif : </h3>");
            out.println("<ol>");
            for(int i = 0; i < TempNeg.size(); i++){
                out.println("<li>");
                out.println(TempNeg.get(i));
                out.println("</li>");
            }
            out.println("</ol>");
            out.println("<h3> Tweet Netral : </h3>");
            out.println("<ol>");
            for(int i = 0; i < TempNetral.size(); i++){
                out.println("<li>");
                out.println(TempNetral.get(i));
                out.println("</li>");
            }
            out.println("</ol>");
            out.println("</body>");
            out.println("</html>");
        }
        
     }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP
     * <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>
}
