package com.fortythreesunsets.hairsalon;

import com.fortythreesunsets.hairsalon.entities.*;
import com.fortythreesunsets.hairsalon.repositories.*;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@SpringBootApplication
public class HairsalonApplication {

	public static void main(String[] args) {

		ApplicationContext context = SpringApplication.run(HairsalonApplication.class, args);

		// CITA
		AppointmentRepository appointmentRepository = context.getBean(AppointmentRepository.class);

		Appointment cita1 = new Appointment(null, LocalDateTime.of(2022, 1, 14, 10, 30), 40, "Corte de pelo degradado");
		appointmentRepository.save(cita1);

		Appointment cita2 = new Appointment(null, LocalDateTime.of(2022, 1,1, 13, 30), 70, "Corte de pelo bob largo");
		appointmentRepository.save(cita2);

		Appointment cita3 = new Appointment(null, LocalDateTime.of(2022, 1,14, 16, 30), 120, "Corte de pelo buzzcut");
		appointmentRepository.save(cita3);

		Appointment cita4 = new Appointment(null, LocalDateTime.of(2022, 1,31, 20, 00), 280, "Peinado recogido");
		appointmentRepository.save(cita4);

		Appointment cita5 = new Appointment(null, LocalDateTime.of(2022, 2,1, 15, 00), 120, "Color tradicional");
		appointmentRepository.save(cita5);

		// CLIENTE
		CustomerRepository customerRepository = context.getBean(CustomerRepository.class);

		Customer cliente1 = new Customer(null, "Travis", "Mahony", "tmahony0@wikispaces.com", "5555909734", LocalDate.of(1990, 1, 3));
		customerRepository.save(cliente1);

		Customer cliente2= new Customer(null, "Elianore", "Gippes", "egippes2@mapy.cz", "5555522024", LocalDate.of(1984, 6, 17));
		customerRepository.save(cliente2);

		// Asociación Appointment - Customer
		cita1.setCustomer(cliente1);
		appointmentRepository.save(cita1);

		cita2.setCustomer(cliente2);
		appointmentRepository.save(cita2);

		cita3.setCustomer(cliente1);
		appointmentRepository.save(cita3);

		cita4.setCustomer(cliente2);
		appointmentRepository.save(cita4);

		cita5.setCustomer(cliente2);
		appointmentRepository.save(cita5);


		// Comprobar que al buscar el appointment, se recupera también el customer
		Optional<Appointment> appointmentOptional = appointmentRepository.findById(1L);
		Appointment appointment1 = null;
		if (appointmentOptional.isPresent()) {
			appointment1 = appointmentOptional.get();
			System.out.println(appointment1.getCustomer());
		}

		// Escenario inverso: Como Customer no es propietario de la asociación, entonces no se guarda en la BD esa asociación
		Appointment cita6 = new Appointment(null, LocalDateTime.now(), 60, "Mechas Balayage");
		appointmentRepository.save(cita6);

		Appointment cita7 = new Appointment(null, LocalDateTime.now(), 60, "Alaciado orgánico");
		appointmentRepository.save(cita7);

		Customer cliente3 = new Customer(null, "Mary", "Morgan", "marymo@mail.com", "5588974512", LocalDate.of(1994, 4, 15));
		cliente3.getAppointments().add(cita6);
		cliente3.getAppointments().add(cita7);
		customerRepository.save(cliente3);

		cita6.setCustomer(cliente3);
		appointmentRepository.save(cita6);

		cita7.setCustomer(cliente3);
		appointmentRepository.save(cita7);

		// SERVICIO
		TreatmentRepository treatmentRepository = context.getBean(TreatmentRepository.class);

		Treatment cortePeloH= new Treatment(null, "Corte de pelo hombre", 200d, 40);
		treatmentRepository.save(cortePeloH);

		Treatment cortePeloM= new Treatment(null, "Corte de pelo mujer", 300d, 70);
		treatmentRepository.save(cortePeloM);

		Treatment peinado = new Treatment(null, "Peinado", 250d, 60);
		treatmentRepository.save(peinado);

		Treatment tinte = new Treatment(null, "Tinte", 450d, 120);
		treatmentRepository.save(tinte);

		Treatment mechas = new Treatment(null, "Mechas", 700d, 240);
		treatmentRepository.save(mechas);

		Treatment alaciado = new Treatment(null, "Alaciado", 400d, 120);
		treatmentRepository.save(alaciado);

		// Asociación Treatment - Appointment
		cita1.setTreatment(cortePeloH);	// Asociación ManyToOne
		appointmentRepository.save(cita1);

		cita2.setTreatment(cortePeloM);
		appointmentRepository.save(cita2);

		cita3.setTreatment(cortePeloH);
		appointmentRepository.save(cita3);

		cita4.setTreatment(peinado);
		appointmentRepository.save(cita4);

		cita5.setTreatment(tinte);
		appointmentRepository.save(cita5);

		cita6.setTreatment(mechas);
		appointmentRepository.save(cita6);

		cita7.setTreatment(alaciado);
		appointmentRepository.save(cita7);


		// EMPLEADO
		EmployeeRepository employeeRepository = context.getBean(EmployeeRepository.class);

		Employee empleado1 = new Employee(null, "Antonio", "Romano",LocalDate.of(1990, 6, 14), "antorom@mail.com", "002671787", "ROGA900614HK3");
		employeeRepository.save(empleado1);

		Employee empleado2 = new Employee(null, "Erich", "Hurling",LocalDate.of(1991, 10, 26), "ehurling0@geocities.com", "235930803", "HUTE911026TL8");
		employeeRepository.save(empleado2);

		Employee empleado3 = new Employee(null, "Juliana", "Willis",LocalDate.of(1987, 03, 18), "jwillis4@sakura.ne.jp", "116386158", "WICJ870318MV2");
		employeeRepository.save(empleado3);

		// Asociación Appointment - Employee
		cita1.setEmployee(empleado1);
		cita2.setEmployee(empleado1);
		cita3.setEmployee(empleado2);
		cita4.setEmployee(empleado3);
		cita5.setEmployee(empleado2);
		cita6.setEmployee(empleado3);
		cita7.setEmployee(empleado1);

		appointmentRepository.saveAll(List.of(cita1, cita2, cita3, cita4, cita5, cita6, cita7));


		// DIRECCIÓN
		AddressRepository addressRepository = context.getBean(AddressRepository.class);

		Address direccion1 = new Address(null, "Goodland Trail 11052", "52060", "CDMX");
		// @OneToOne(cascade = CascadeType.ALL) en Employee también guarda la dirección
		//addressRepository.save(direccion1);

		Address direccion2 = new Address(null, "Donald Junction 8", "51690", "CDMX");
		//addressRepository.save(direccion2);

		Address direccion3 = new Address(null, "Mallory Circle 36", "56617", "CDMX");
		//addressRepository.save(direccion3);

		// Asociación Employee - Address
		empleado1.setAddress(direccion1);
		employeeRepository.save(empleado1);

		empleado2.setAddress(direccion2);
		employeeRepository.save(empleado2);

		empleado3.setAddress(direccion3);
		employeeRepository.save(empleado3);
	}

}
