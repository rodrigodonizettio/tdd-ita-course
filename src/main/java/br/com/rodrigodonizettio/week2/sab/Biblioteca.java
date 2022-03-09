package br.com.rodrigodonizettio.week2.sab;

public class Biblioteca {
    /***
     * This class (Library) belongs to SAB/Sistema de Automação em Bibliotecas (LAS/Library Automation System)
     * The below code is for exercise purpose only. This way its dependencies weren't imported.
     ***/

    /*********** BEFORE:
     if (nome != null) {
        if (!nome.isEmpty()) {
            Usuario usuario = new Usuario(nome);
            if (!_usuarios.contains(usuario)) {
                _usuarios.add(usuario);
            } else
                throw new UsuarioJaRegistradoException("--->J� existe usu�rio com o nome \""
                    + nome + "\"! Use outro nome!");
        } else
            throw new UsuarioComNomeVazioException("--->N�o pode registrar usuario com nome vazio!");
     } else
        throw new UsuarioInexistenteException("--->N�o pode registrar usuario inexistente!");
     ***********/



    /***
     * The below code is for exercise purpose only. It reflects the refactored above code removing some CodeSmells.
     * - Removed unnecessary ELSE clause
     * - Removed NOT ('!') boolean modifier for easier understanding
     * * It was used inline code as the exercise requires, but I prefer the brackets to make easier to see the code scope
     ***/

    /*********** AFTER:
     if (null == nome) throw new UsuarioInexistenteException("--->N�o pode registrar usuario inexistente!");
     if (nome.isEmpty()) throw new UsuarioComNomeVazioException("--->N�o pode registrar usuario com nome vazio!");

     Usuario usuario = new Usuario(nome);

     if (_usuarios.contains(usuario)) throw new UsuarioJaRegistradoException("--->J� existe usu�rio com o nome \""
        + nome + "\"! Use outro nome!");
     _usuarios.add(usuario);
     ***********/
}
