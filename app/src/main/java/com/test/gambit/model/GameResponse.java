package com.test.gambit.model;

import java.util.ArrayList;

/**
 * Created by sunilparmar on 4/27/2019.
 */

public class GameResponse {
    private ArrayList<Data> data;

    public ArrayList<Data> getData() {
        return data;
    }

    public void setData(ArrayList<Data> data) {
        this.data = data;
    }

    public class Data {

        private int id;
        private String date;
        private int homeTeamScore;
        private int visitorTeamScore;
        private int season;
        private int period;
        private String status;
        private String time;
        private boolean postseason;
        private HomeTeam home_team;
        private VisitorTeam visitor_team;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getDate() {
            return date;
        }

        public void setDate(String date) {
            this.date = date;
        }

        public int getHomeTeamScore() {
            return homeTeamScore;
        }

        public void setHomeTeamScore(int homeTeamScore) {
            this.homeTeamScore = homeTeamScore;
        }

        public int getVisitorTeamScore() {
            return visitorTeamScore;
        }

        public void setVisitorTeamScore(int visitorTeamScore) {
            this.visitorTeamScore = visitorTeamScore;
        }

        public int getSeason() {
            return season;
        }

        public void setSeason(int season) {
            this.season = season;
        }

        public int getPeriod() {
            return period;
        }

        public void setPeriod(int period) {
            this.period = period;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public String getTime() {
            return time;
        }

        public void setTime(String time) {
            this.time = time;
        }

        public boolean isPostseason() {
            return postseason;
        }

        public void setPostseason(boolean postseason) {
            this.postseason = postseason;
        }


        public HomeTeam getHome_team() {
            return home_team;
        }

        public void setHome_team(HomeTeam home_team) {
            this.home_team = home_team;
        }

        public VisitorTeam getVisitor_team() {
            return visitor_team;
        }

        public void setVisitor_team(VisitorTeam visitor_team) {
            this.visitor_team = visitor_team;
        }
    }

    public class HomeTeam {

        private int id;
        private String abbreviation;
        private String city;
        private String conference;
        private String division;
        private String fullName;
        private String name;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getAbbreviation() {
            return abbreviation;
        }

        public void setAbbreviation(String abbreviation) {
            this.abbreviation = abbreviation;
        }

        public String getCity() {
            return city;
        }

        public void setCity(String city) {
            this.city = city;
        }

        public String getConference() {
            return conference;
        }

        public void setConference(String conference) {
            this.conference = conference;
        }

        public String getDivision() {
            return division;
        }

        public void setDivision(String division) {
            this.division = division;
        }

        public String getFullName() {
            return fullName;
        }

        public void setFullName(String fullName) {
            this.fullName = fullName;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

    }

    public class VisitorTeam {

        private int id;
        private String abbreviation;
        private String city;
        private String conference;
        private String division;
        private String fullName;
        private String name;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getAbbreviation() {
            return abbreviation;
        }

        public void setAbbreviation(String abbreviation) {
            this.abbreviation = abbreviation;
        }

        public String getCity() {
            return city;
        }

        public void setCity(String city) {
            this.city = city;
        }

        public String getConference() {
            return conference;
        }

        public void setConference(String conference) {
            this.conference = conference;
        }

        public String getDivision() {
            return division;
        }

        public void setDivision(String division) {
            this.division = division;
        }

        public String getFullName() {
            return fullName;
        }

        public void setFullName(String fullName) {
            this.fullName = fullName;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

    }
}
