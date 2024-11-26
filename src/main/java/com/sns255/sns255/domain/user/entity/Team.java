package com.sns255.sns255.domain.user.entity;

public enum Team {
    BLUE,
    GREEN,
    RED;

    public static Team getBalancedTeam(long blueCount, long greenCount, long redCount) {
        if (blueCount <= greenCount && blueCount <= redCount) {
            return Team.BLUE;
        } else if (greenCount <= blueCount && greenCount <= redCount) {
            return Team.GREEN;
        } else {
            return Team.RED;
        }
    }
}
