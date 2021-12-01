package com.fourmuchachos.gifmycrypto.Config;

import com.fourmuchachos.gifmycrypto.Gif.Entity.Gif;
import com.fourmuchachos.gifmycrypto.Gif.Repo.GifRepo;
import com.fourmuchachos.gifmycrypto.User.Entity.Role;
import com.fourmuchachos.gifmycrypto.User.Repo.RoleRepo;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class DataSetup implements CommandLineRunner {

    private final RoleRepo roleRepo;
    private final GifRepo gifRepo;

    public DataSetup(RoleRepo roleRepo, GifRepo gifRepo) {
        this.roleRepo = roleRepo;
        this.gifRepo = gifRepo;
    }

    @Override
    public void run(String... args) throws Exception {
        Role user = new Role(Role.USER);
        Role admin = new Role(Role.ADMIN);

        roleRepo.save(user);
        roleRepo.save(admin);

        List<Gif> gifs = List.of(
                new Gif(null,1,"https://media.giphy.com/media/DnMMGxEvniha7CvASq/giphy.gif","Rocket, to the moon"),
                new Gif(null,1,"https://media.giphy.com/media/oNFP9kltPi7fp8TUAV/giphy.gif","Elon Musk, to the moon"),
                new Gif(null,1,"https://media.giphy.com/media/IwAZ6dvvvaTtdI8SD5/giphy.gif","The Office, Michael and Dwight party"),
                new Gif(null,1,"https://media.giphy.com/media/sVnKj2wDhUTsFKFWhx/giphy.gif","The Office, Michael and Erin party"),
                new Gif(null,1,"https://media.giphy.com/media/3o7abldj0b3rxrZUxW/giphy.gif","Carlton, backflip"),
                new Gif(null,1,"https://media.giphy.com/media/6oMKugqovQnjW/giphy.gif","Guy dancing in chair"),
                new Gif(null,1,"https://media.giphy.com/media/6nuiJjOOQBBn2/giphy.gif","HIMYM, Barney celebrating"),
                new Gif(null,1,"https://media.giphy.com/media/YTRUPHI7fXK6s/giphy.gif","Girl bathing in money"),
                new Gif(null,1,"https://media.giphy.com/media/la6Ne7z15BXs4/giphy.gif","Rich Smurf"),
                new Gif(null,1,"https://media.giphy.com/media/26FPLMDDN5fJCir0A/giphy.gif","Scrooge McDuck"),
                new Gif(null,1,"https://media.giphy.com/media/3NtY188QaxDdC/giphy.gif","Zootopia, happy sloth"),
                new Gif(null,1,"https://media.giphy.com/media/5VKbvrjxpVJCM/giphy.gif","Parks and Recreation, Chris Pratt wow"),
                new Gif(null,-1,"https://media.giphy.com/media/l1KVaj5UcbHwrBMqI/giphy.gif","Toddler girl crying"),
                new Gif(null,-1,"https://media.giphy.com/media/9Y5BbDSkSTiY8/giphy.gif","Sad toddler"),
                new Gif(null,-1,"https://media.giphy.com/media/W0c3xcZ3F1d0EYYb0f/giphy.gif","Friend, sad Chandler watching the rain"),
                new Gif(null,-1,"https://media.giphy.com/media/YLgIOmtIMUACY/giphy.gif","The Office, Pam being sad"),
                new Gif(null,-1,"https://media.giphy.com/media/l22ysLe54hZP0wubek/giphy.gif","Boy 'is fine' and crying"),
                new Gif(null,-1,"https://media.giphy.com/media/1BXa2alBjrCXC/giphy.gif","Anchorman, crying with wine"),
                new Gif(null,-1,"https://media.giphy.com/media/gKW26O7cU2JDG/giphy.gif","Simpsons, sad Ralph"),
                new Gif(null,-1,"https://media.giphy.com/media/hPSMHzgHQeFuE/giphy.gif","Tom Cruise, crying"),
                new Gif(null,-1,"https://media.giphy.com/media/fw515tAEVEzXNDTuWT/giphy.gif","Parks and Recreation, Leslie falls into pit"),
                new Gif(null,0,"https://media.giphy.com/media/jPAdK8Nfzzwt2/giphy.gif","Elmo, shrug")
        );

        gifRepo.saveAll(gifs);

    }
}
