package model;

import java.io.*;
import java.util.ArrayList;

public class PessoaJuridicaRepo {
    private ArrayList<PessoaJuridica> lista = new ArrayList<>();

    public void inserir(PessoaJuridica pj) {
        lista.add(pj);
    }

    public void alterar(PessoaJuridica pj) {
        for (int i = 0; i < lista.size(); i++) {
            if (lista.get(i).getId() == pj.getId()) {
                lista.set(i, pj);
                return;
            }
        }
    }

    public void excluir(int id) {
        lista.removeIf(p -> p.getId() == id);
    }

    public PessoaJuridica obter(int id) {
        for (PessoaJuridica p : lista) {
            if (p.getId() == id) return p;
        }
        return null;
    }

    public ArrayList<PessoaJuridica> obterTodos() {
        return lista;
    }

    public void persistir(String arquivo) throws Exception {
        ObjectOutputStream out =
                new ObjectOutputStream(new FileOutputStream(arquivo));
        out.writeObject(lista);
        out.close();
    }

    public void recuperar(String arquivo) throws Exception {
        ObjectInputStream in =
                new ObjectInputStream(new FileInputStream(arquivo));
        lista = (ArrayList<PessoaJuridica>) in.readObject();
        in.close();
    }
}
