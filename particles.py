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
    def update(self) -> None:
        ...

    @abstractmethod
    def draw(self, window, x, y) -> None:
        ...

class Sand(Particle):
    def __init__(self):
        self._COLOR = (194, 178, 128)

    def update(self) -> None:
        pass

    def draw(self, window, x, y) -> None:
        pygame.draw.rect(window, self.color, (x*10, y*10, PARTICLE_WIDTH, PARTICLE_HEIGHT))
