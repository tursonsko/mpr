package pl.pjatk.gameplay.service;

import org.springframework.stereotype.Service;
import pl.pjatk.gameplay.model.Player;
import pl.pjatk.gameplay.repository.PlayerRepository;

import java.util.Optional;

@Service
public class DamageService {


    private PlayerRepository playerRepository;

//    public DamageService() {
//
//    }

    public DamageService(PlayerRepository playerRepository) {
        this.playerRepository = playerRepository;
    }



    public Player damage(Long idAttack, Long idVictim) {
        Optional<Player> attacker = playerRepository.findById(idAttack);
        Optional<Player> victim = playerRepository.findById(idVictim);

        if(attacker.isPresent() && victim.isPresent()) {
            victim.get().setHealth(victim.get().getHealth() - attacker.get().getAttack());
            return playerRepository.save(victim.get());
        } else {
            return null;
        }
    }

    public Player heal(Player player) {
        if(player.getHealth() < 30) {
            player.setHealth(100);
        }
        return player;
    }

    public Player setMoreMana(Player player) {
       player.setMana(player.getMana() * 2);
       return player;
    }

    public int morePowerOfAttack(Player player, int newAttack) {

        if(newAttack > 50) {
            System.out.println("too much attack");
        } else {
            int boostAttack = player.getAttack() + newAttack;
            player.setAttack(boostAttack);
        }
       return player.getAttack();
    }

    public void changeNickname(Player player, String newName){
        if(newName.length()> 10) {
            System.out.println("too long name");
        }
        player.setNickname(newName);
    }


}
