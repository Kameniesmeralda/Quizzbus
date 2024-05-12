package quizzbus.commun;

import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

import quizzbus.data.Astuce;
import quizzbus.data.Compte;
import quizzbus.data.Configuration_Poste;
import quizzbus.data.Joueur;
import quizzbus.data.Quizz;


@Mapper
public interface IMapper {
	
	Compte update( @MappingTarget Compte target, Compte source  );
	Quizz update( @MappingTarget Quizz target, Quizz source  );
	Configuration_Poste update( @MappingTarget Configuration_Poste target, Configuration_Poste source  );
	Joueur update( @MappingTarget Joueur target, Joueur source  );
	Astuce update( @MappingTarget Astuce target, Astuce source  );
}
