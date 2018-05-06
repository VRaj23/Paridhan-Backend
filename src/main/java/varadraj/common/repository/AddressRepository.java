package varadraj.common.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import varadraj.common.model.address.Address;

public interface AddressRepository extends CrudRepository<Address, Long>{

	Optional<Address> findByAddressID(long addressID);
}
