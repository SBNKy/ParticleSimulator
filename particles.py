import random
from abc import ABC, abstractmethod
from math import floor

import pygame.draw

from config import SCREEN_WIDTH, SCREEN_HEIGHT

PARTICLE_WIDTH = 10
PARTICLE_HEIGHT = 10

class Particle(ABC):
    _COLOR = tuple[int, int, int]

    @property
    def color(self) -> tuple[int, int, int]:
        return self._COLOR

    @abstractmethod
    def update(self, grid, col, row, total_cols, total_rows) -> None:
        ...

    @abstractmethod
    def draw(self, window, col, row) -> None:
        ...

class Sand(Particle):
    def __init__(self):
        self._COLOR = (194, 178, 128)

    def update(self, grid, col, row, total_cols, total_rows) -> None:
        if row+1 == total_rows:
            return

        if grid[row+1][col] is None:
            grid[row][col] = None
            grid[row+1][col] = self


    def draw(self, window, col, row) -> None:
        pygame.draw.rect(window, self.color, (col*10, row*10, PARTICLE_WIDTH, PARTICLE_HEIGHT))
