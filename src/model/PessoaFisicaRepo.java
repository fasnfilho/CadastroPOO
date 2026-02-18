package model;

import java.io.*;
import java.util.ArrayList;

public class PessoaFisicaRepo {
    private ArrayList<PessoaFisica> lista = new ArrayList<>();

    public void inserir(PessoaFisica pf) {
        lista.add(pf);
    }

    public void alterar(PessoaFisica pf) {
        for (int i = 0; i < lista.size(); i++) {
            if (lista.get(i).getId() == pf.getId()) {
                lista.set(i, pf);
                return;
            }
        }
    }

    public void excluir(int id) {
        lista.removeIf(p -> p.getId() == id);
    }

    public PessoaFisica obter(int id) {
        for (PessoaFisica p : lista) {
            if (p.getId() == id) return p;
        }
        return null;
    }

    public ArrayList<PessoaFisica> obterTodos() {
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
        lista = (ArrayList<PessoaFisica>) in.readObject();
        in.close();
    }
}
