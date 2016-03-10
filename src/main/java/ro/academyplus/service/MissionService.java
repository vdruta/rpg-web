package ro.academyplus.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ro.academyplus.dto.validators.MissionDTO;
import ro.academyplus.model.Mission;
import ro.academyplus.model.characters.Hero;
import ro.academyplus.model.characters.Villain;

import java.util.List;

/**
 * Created by MM on 2016-03-10.
 */
@Service
public class MissionService {

    public void updateMission(MissionDTO missionDTO) {
        Mission mission = Mission.getInstance(missionDTO.getHero().getLevel());
        if (missionDTO.getSelectedAction().compareTo("Up") == 1) {
                if (upIsMonster(mission.getMap(), mission.getVillains(), mission.getWidth()))
                    return;
                else if (upIsBorder)
                    mission.win = true;



                //check if monster up, 50% run, 50% fight
                    //fight
                        //check border
                        //win + move hero up + new artefact with random coeficient + experience//
                        //lose + finish mission//
                    //run
                        //move hero up
                //if no monster, check if border WIN MISSION.

        }
    }

    public boolean upIsMonster(int[][] map, List<Villain> villains, int width) {
        for(int i = 0; i < width; i++) {
            for(int j = 0; j < width; j++) {
                if (map[i][j] == 2) {
                    if (map[i - 1][j] == 1)
                        return true;
                }
            }
        }
        return false;
    }
}
