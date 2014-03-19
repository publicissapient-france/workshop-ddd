package com.xebia.domain.credit;

import java.util.List;

public interface CreditRepository {

  public List<Credit> findAll();

  public Credit findOne(Long id);

  public void save(Credit credit);

}
