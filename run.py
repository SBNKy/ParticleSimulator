import pygame
import sys
from config import SCREEN_WIDTH, SCREEN_HEIGHT, FPS, BLACK_COLOR
from grid import Grid

pygame.init()

def main():
    window = pygame.display.set_mode((SCREEN_WIDTH, SCREEN_HEIGHT))
    pygame.display.set_caption("Particle Simulator")
    clock = pygame.time.Clock()

    grid = Grid()

    while True:
        window.fill(BLACK_COLOR)
        for event in pygame.event.get():
            if event.type == pygame.QUIT:
                pygame.quit()
                sys.exit()

        if pygame.mouse.get_pressed()[0]:
            grid.create_particle(pygame.mouse.get_pos())

        grid.traverse_grid(window)

        pygame.display.update()
        clock.tick(FPS)

if __name__ == '__main__':
    main()