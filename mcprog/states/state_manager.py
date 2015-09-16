from mcprog.states.game_state import GameState

__author__ = 'mcprog'


class StateManager:

    STATES = ()

    def __init__(self, start_state):
        self.state = start_state

    def switch(self, new_state):
        self.state = new_state
            

