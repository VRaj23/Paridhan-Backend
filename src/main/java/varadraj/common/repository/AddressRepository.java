package varadraj.common.repository;

import org.springframework.data.repository.CrudRepository;

import varadraj.common.model.address.Address;

public interface AddressRepository extends CrudRepository<Address, Long>{

	Address findByAddressID(long addressID);
}
