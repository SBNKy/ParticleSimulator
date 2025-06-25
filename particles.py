import random
from abc import ABC, abstractmethod
from math import floor

import pygame.draw

from config import SCREEN_WIDTH, SCREEN_HEIGHT

PARTICLE_WIDTH = 10
PARTICLE_HEIGHT = 10

class Particle(ABC):
    _x: int
    _y: int

    def __init__(self, x: int, y: int):
        self._x = x
        self._y = y

    @property
    def x(self) -> int:
        return self._x

    @property
    def y(self) -> int:
        return self._y

    @property
    def color(self) -> tuple[int, int, int]:
        return self.__class__.COLOR

    def _get_row_and_col(self):
        return floor(self.x/10), floor(self.y/10)

    @abstractmethod
    def update(self) -> None:
        ...

    @abstractmethod
    def draw(self, window) -> None:
        ...

    def _in_bounds(self, x ,y) -> bool:
        return 0 <= x <= SCREEN_WIDTH - PARTICLE_WIDTH and 0 <= y < SCREEN_HEIGHT - PARTICLE_HEIGHT


class Sand(Particle):
    COLOR = (194, 178, 128)

    def __init__(self, x: int, y: int):
        super().__init__(x, y)

    def update(self, grid, rows) -> None:
        print(rows)
        if not self._in_bounds(self.x, self.y):
            return

        col, row = self._get_row_and_col()
        print(f"-----------------------------------------------------------------------\nROW={row}")
        if row+1 > rows:
            print("ekran")
            return

        if row + 1 < rows and grid[row+1][col] is None:
            self._y = (col+1) * 10
            return

        directions = []
        if self._in_bounds(row-1, col+1) and grid[row-1][col+1] is None:
            directions.append((-1, 1))
        if self._in_bounds(row+1, col+1) and grid[row+1][col+1] is None:
            directions.append((1, 1))

        if directions:
            dx, dy = random.choice(directions)
            grid[row][col] = None
            self._x = (row + dx) * PARTICLE_WIDTH
            self._y = (col + dy) * PARTICLE_HEIGHT
            grid[row+dx][col+dy] = self


    def draw(self, window) -> None:
        pygame.draw.rect(window, self.color, (self.x, self.y, PARTICLE_WIDTH, PARTICLE_HEIGHT))
        print(f'x={self.x}, y={self.y}')
