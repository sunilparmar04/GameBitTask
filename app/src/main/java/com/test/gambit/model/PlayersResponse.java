package com.test.gambit.model;

import java.util.ArrayList;

/**
 * Created by sunilparmar on 4/27/2019.
 */

public class PlayersResponse {

    private ArrayList<Data> data;

   public class Data {
        private int id;
        private String firstName;
        private String lastName;
        private String position;
        private int heightFeet;
        private int heightInches;
        private int weightPounds;
        private Team team;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getFirstName() {
            return firstName;
        }

        public void setFirstName(String firstName) {
            this.firstName = firstName;
        }

        public String getLastName() {
            return lastName;
        }

        public void setLastName(String lastName) {
            this.lastName = lastName;
        }

        public String getPosition() {
            return position;
        }

        public void setPosition(String position) {
            this.position = position;
        }

        public int getHeightFeet() {
            return heightFeet;
        }

        public void setHeightFeet(int heightFeet) {
            this.heightFeet = heightFeet;
        }

        public int getHeightInches() {
            return heightInches;
        }

        public void setHeightInches(int heightInches) {
            this.heightInches = heightInches;
        }

        public int getWeightPounds() {
            return weightPounds;
        }

        public void setWeightPounds(int weightPounds) {
            this.weightPounds = weightPounds;
        }

        public Team getTeam() {
            return team;
        }

        public void setTeam(Team team) {
            this.team = team;
        }

    }


 public    class Team {

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

    public ArrayList<Data> getData() {
        return data;
    }

    public void setData(ArrayList<Data> data) {
        this.data = data;
    }
}