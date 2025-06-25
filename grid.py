from math import floor
from typing import List
from particles import Particle
from config import SCREEN_WIDTH, SCREEN_HEIGHT
from particles import PARTICLE_WIDTH, PARTICLE_HEIGHT, Sand

class Grid:
    _rows: int
    _cols: int
    _grid: List[List[Particle]]

    def __init__(self):
        self._grid = self._create_empty_grid()

    @property
    def rows(self):
        return self._rows

    @property
    def cols(self):
        return self._cols


    def _create_empty_grid(self) -> List[List[None]]:
        self._rows, self._cols = round(SCREEN_WIDTH / PARTICLE_WIDTH), round(SCREEN_HEIGHT / PARTICLE_HEIGHT)
        # grid = [[Sand(row * PARTICLE_WIDTH, col * PARTICLE_HEIGHT).y for col in range(self.cols)] for row in range(self.rows)]
        grid = [[None for _ in range(self.cols)] for _ in range(self.rows)]
        return grid

    def traverse_grid(self, window) -> None:
        for row in reversed(range(self.rows)):
            for col in reversed(range(self.cols)):
                particle = self._grid[row][col]
                if particle:
                    particle.update(self._grid, self.cols)
                    particle.draw(window)

    def create_particle(self, mouse_position: tuple[int, int]) -> None:
        x, y = mouse_position
        if not self._mouse_within_screen(x, y):
            return

        row, col = floor(x/10), floor(y/10)
        if self._grid[row][col] is None:
            self._grid[row][col] = Sand(row * PARTICLE_WIDTH, col * PARTICLE_HEIGHT)


    def _mouse_within_screen(self, x: int, y: int) -> bool:
        return 0 <= x <= SCREEN_WIDTH - PARTICLE_WIDTH and 0 <= y <= SCREEN_HEIGHT - PARTICLE_HEIGHT