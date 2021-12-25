package com.filter;

import java.io.*;
import java.util.*;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.pipe.AllowedWords;

/**
 * Servlet implementation class StroryParser
 */
@WebServlet("/StroryParser")
public class StroryParser extends HttpServlet implements AllowedWords {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public StroryParser() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		PrintWriter out = response.getWriter();
		
		HttpSession session = request.getSession();
		String path = (String)session.getAttribute("path");
		BufferedReader reader = new BufferedReader(new FileReader(path));

        String line;
        List<String> arrayLine = new ArrayList<String>();

        int count = -2;

        while ((line = reader.readLine()) != null) {
            arrayLine.add(line);

            count++;
        }

        String[] storyUsers = new String[count];

        for (int i = 0; i < count; i++) {
            storyUsers[i] = arrayLine.get(i + 1).substring(arrayLine.get(i + 1).indexOf(":") + 1);
            storyUsers[i].trim();
            storyUsers[i] = storyUsers[i].substring(storyUsers[i].indexOf("\"") + 1);
            storyUsers[i] = storyUsers[i].substring(0, storyUsers[i].indexOf("\""));
            storyUsers[i] = storyUsers[i].toUpperCase();
        }

        List<String> storyList = new ArrayList<String>();
        List<String> storyListWithFormalIndicatorFormat = Arrays.asList(storyUsers);
        List<String> errorList = new ArrayList<String>();

        storyListWithFormalIndicatorFormat = indicatorCheck(0, storyListWithFormalIndicatorFormat, errorList);

        List<String> roleList = new ArrayList<String>();
        List<String> meanList = new ArrayList<String>();
        List<String> endList = new ArrayList<String>();

        for (int i = 0; i < storyListWithFormalIndicatorFormat.size(); i++) {
            String role = storyListWithFormalIndicatorFormat.get(i).split(",")[0];
            String mean = storyListWithFormalIndicatorFormat.get(i).substring(storyListWithFormalIndicatorFormat.get(i).indexOf(",") + 2).split(",")[0];
            String end = storyListWithFormalIndicatorFormat.get(i).substring(storyListWithFormalIndicatorFormat.get(i).lastIndexOf(",") + 2);

            roleList.add(role);
            meanList.add(mean);
            endList.add(end);
        }

        List<String> roleUserList = new ArrayList<String>();

        for (int i = 0; i < roleList.size(); i++) {
            String lastLetters = roleList.get(i).substring(roleList.get(i).length() - 3);

            List<String> words = new ArrayList<String>(Arrays.asList(roleList.get(i).split(" ")));

            if (words.size() == 2 && !lastLetters.equals("ING")) {
                roleUserList.add(storyListWithFormalIndicatorFormat.get(i));
            } else if (words.size() == 3 && !lastLetters.equals("ING")) {
                if (words.get(1).equals("A") || words.get(1).equals("AN")) {
                    roleUserList.add(storyListWithFormalIndicatorFormat.get(i));
                } else {
                    errorList.add(storyListWithFormalIndicatorFormat.get(i));
                }
            } else {
                errorList.add(storyListWithFormalIndicatorFormat.get(i));
            }
        }


        System.out.println("\nThe user stories list");
        for (int i = 0; i < roleUserList.size(); i++) {
            out.println(roleUserList.get(i));
        }

        errorList = new ArrayList<>(new HashSet<>(errorList));
        System.out.println("\nThe errors are in these user stories");
        for (int i = 0; i < errorList.size(); i++) {
            out.println(errorList.get(i));
        }
        reader.close();
        
        
        session.setAttribute("accepted_stories", roleUserList);
        session.setAttribute("unaccepted_stories",errorList);
        response.sendRedirect(request.getContextPath()+"/viewStories.jsp");
	}

	static List<String> indicatorCheck(int k, List<String> storyUsersList, List<String> errorList) {
        List<String> indicatorWords = new ArrayList<String>();
        List<String> indicatorApplied = new ArrayList<String>();

        if (k == 0) {
            indicatorWords.add("AS");
            indicatorWords.add("AS A");
            indicatorWords.add("AS AN");
        } else {
            indicatorWords.add(", I'M ABLE TO");
            indicatorWords.add(", I AM ABLE TO");
            indicatorWords.add(", I WANT TO");
            indicatorWords.add(", I WISH TO");
            indicatorWords.add(", I CAN");
            indicatorWords.add(", I WANT");
            indicatorWords.add(", I SHOULD BE ABLE TO");
        }

        for (int i = 0; i < storyUsersList.size(); i++) {
            boolean indicatorWordsIsFound = false;

            for (int j = 0; j < indicatorWords.size(); j++) {
                indicatorWordsIsFound = storyUsersList.get(i).contains(indicatorWords.get(j));

                if (indicatorWordsIsFound) {
                    break;
                }
            }
            if (indicatorWordsIsFound) {
                indicatorApplied.add(storyUsersList.get(i));
            }else {
                errorList.add(storyUsersList.get(i));

            }
        }

        if (k != 1) {
            return indicatorCheck(k = k + 1, indicatorApplied, errorList);
        } else {
            return indicatorApplied;
        }
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
    }
	}

