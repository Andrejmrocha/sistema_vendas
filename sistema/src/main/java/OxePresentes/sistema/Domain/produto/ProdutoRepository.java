package OxePresentes.sistema.Domain.produto;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Range;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ProdutoRepository extends JpaRepository<Produto, Long> {
    Produto getReferenceById(Long id);

    Page<Produto> findByNomeContaining(String nome, Pageable paginacao);
}
