package ro.academyplus.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ro.academyplus.dto.ArenaDTO;
import ro.academyplus.model.Arena;

import javax.servlet.ServletContext;

/**
 * Created by MM on 2016-03-16.
 */
@Service
public class ArenaService {

    @Autowired
    ServletContext servletContext;

    public void processArena(ArenaDTO arenaDTO) {
        Arena arena = (Arena) servletContext.getAttribute("arena");
        if (arena.isTourn1()) {
            if (arenaDTO.getSelectedAction().compareTo("Up") == 0 || arenaDTO.getSelectedAction().compareTo("Down") == 0 || arenaDTO.getSelectedAction().compareTo("Left") == 0 || arenaDTO.getSelectedAction().compareTo("Right") == 0) {
                moveHero(1, arenaDTO.getSelectedAction(), arena.getMap(), arena.getWidth());
                arena.setTourn1(false);
                arena.setTourn2(true);
            }
        }
        else if (arena.isTourn2()) {
                if (arenaDTO.getSelectedAction2().compareTo("Up") == 0 || arenaDTO.getSelectedAction2().compareTo("Down") == 0 || arenaDTO.getSelectedAction2().compareTo("Left") == 0 || arenaDTO.getSelectedAction2().compareTo("Right") == 0) {
                moveHero(2, arenaDTO.getSelectedAction2(), arena.getMap(), arena.getWidth());
                arena.setTourn2(false);
                arena.setTourn1(true);
            }
        }
    }

    public void moveHero(int h, String dir, int[][] map, int width) {
        if (dir.compareTo("Up") == 0) {
            for (int i = 0; i < width; i++) {
                for (int j = 0; j < width; j++) {
                    if (map[i][j] == h) {
                        if (i - 1 >= 0 && map[i - 1][j] != 1 && map[i - 1][j] != 2) {
                            map[i - 1][j] = h;
                            map[i][j] = 0;
                            return;
                        }
                    }
                }
            }
        }
        else if (dir.compareTo("Down") == 0) {
            for (int i = 0; i < width; i++) {
                for (int j = 0; j < width; j++) {
                    if (map[i][j] == h) {
                        if (i + 1 <= width && map[i + 1][j] != 1 && map[i + 1][j] != 2) {
                            map[i + 1][j] = h;
                            map[i][j] = 0;
                            return;
                        }
                    }
                }
            }
        }
        else if (dir.compareTo("Left") == 0) {
            for (int i = 0; i < width; i++) {
                for (int j = 0; j < width; j++) {
                    if (map[i][j] == h) {
                        if (j - 1 >= 0 && map[i][j - 1] != 1 && map[i][j - 1] != 2) {
                            map[i][j - 1] = h;
                            map[i][j] = 0;
                            return;
                        }
                    }
                }
            }
        }
        else if (dir.compareTo("Right") == 0) {
            for (int i = 0; i < width; i++) {
                for (int j = 0; j < width; j++) {
                    if (map[i][j] == h) {
                        if (j + 1 <= width && map[i][j + 1] != 1 && map[i][j + 1] != 2) {
                            map[i][j + 1] = h;
                            map[i][j] = 0;
                            return;
                        }
                    }
                }
            }
        }
    }

    public void processArena1(ArenaDTO arenaDTO) {
        Arena arena = (Arena) servletContext.getAttribute("arena");
        if (arena.isTourn1()) {
            if (arenaDTO.getSelectedAction().compareTo("Up") == 0 || arenaDTO.getSelectedAction().compareTo("Down") == 0 || arenaDTO.getSelectedAction().compareTo("Left") == 0 || arenaDTO.getSelectedAction().compareTo("Right") == 0) {
                moveHero(1, arenaDTO.getSelectedAction(), arena.getMap(), arena.getWidth());
                arena.setTourn1(false);
                arena.setTourn2(true);
            }
        }

    }

    public void processArena2(ArenaDTO arenaDTO) {
        Arena arena = (Arena) servletContext.getAttribute("arena");
        if (arena.isTourn2()) {
            if (arenaDTO.getSelectedAction2().compareTo("Up") == 0 || arenaDTO.getSelectedAction2().compareTo("Down") == 0 || arenaDTO.getSelectedAction2().compareTo("Left") == 0 || arenaDTO.getSelectedAction2().compareTo("Right") == 0) {
                moveHero(2, arenaDTO.getSelectedAction2(), arena.getMap(), arena.getWidth());
                arena.setTourn2(false);
                arena.setTourn1(true);
            }
        }
    }
}
