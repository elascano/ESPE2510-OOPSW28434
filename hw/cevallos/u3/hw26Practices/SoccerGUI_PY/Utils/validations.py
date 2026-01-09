import re

class ValidationError(Exception):
    """Error"""
    pass


class TeamValidator:

    @staticmethod
    def validate_players(players):
        if not isinstance(players, int):
            raise ValidationError("Number of players must be an integer")

        if players < 4 or players > 26:
            raise ValidationError(
                "Number of players must be between 4 and 26"
            )

    @staticmethod
    def validate_coach_name(name):
        if not name.strip():
            raise ValidationError("Coach name cannot be empty")

        if not re.fullmatch(r"[A-Za-z ]+", name):
            raise ValidationError(
                "Coach name must contain only letters"
            )

    @staticmethod
    def validate_team_data(name, coach, city, players):
        if not name.strip():
            raise ValidationError("Team name cannot be empty")

        if not city.strip():
            raise ValidationError("City cannot be empty")

        TeamValidator.validate_coach_name(coach)
        TeamValidator.validate_players(players)