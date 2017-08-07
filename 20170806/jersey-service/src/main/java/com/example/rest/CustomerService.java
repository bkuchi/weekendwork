/* Copyright © 2015 Oracle and/or its affiliates. All rights reserved. */

package com.example.rest;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.stream.Collectors;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import static java.util.stream.Collectors.toList;

@Path("/customers")
public class CustomerService {

  private final CopyOnWriteArrayList<Customer> cList = CustomerList.getInstance();

  @GET
  @Path("/all")
  @Produces(value = {MediaType.TEXT_PLAIN,MediaType.APPLICATION_JSON,MediaType.APPLICATION_XML})
  public List<Customer> getAllCustomers() {
    return
         cList.stream().collect(toList());
       // .map(c -> c.toString())
        //.collect(Collectors.joining("\n"));
  }

  @GET
  @Path("{id}")
  @Produces(MediaType.TEXT_PLAIN)
  public String getCustomer(@PathParam("id") long id) {
    Optional<Customer> match
        = cList.stream()
        .filter(c -> c.getId() == id)
        .findFirst();
    
    if (match.isPresent()) {
      return "---Customer---\n" + match.get().toString();
    } else {
      return "Customer not found";
    }
  }
}
