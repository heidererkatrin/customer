using NUnit.Framework;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using RestSharp;
using System.Net;
using FluentAssertions;
using KundenProjekt;
using KundenProjekt.Model;
using KundenProjekt.Models;
using Xunit;

namespace KundenTest
{
    public class CustomerTest
    {
        [Fact]
        public void StatusCodeTest()
        {
            RestClient client = new RestClient("http://localhost:8080/api/v1/customer/");
            RestRequest request = new RestRequest("Vorname/Nachname", Method.GET);

            IRestResponse response = client.Execute(request);

            response.StatusCode.Should().Be(HttpStatusCode.OK);
        }

        [Fact]
        public void ContentTypeTest()
        {
            RestClient client = new RestClient("http://localhost:8080/api/v1/customer/");
            RestRequest request = new RestRequest("Vorname/Nachname", Method.GET);

            IRestResponse response = client.Execute(request);

            response.ContentType.Should().Be("application/json");
        }


        [Fact]
        public void CustomerServiceGetAll()
        {
            CustomerService customerService = new CustomerService();
            List<Customer> customers = customerService.GetAllCustomers();
            customers.Should().NotBeNull();
        }


        [Fact]
        public void CustomerTestCreateGetAndDelete()
        {
            string firstName = "Hans";
            string lastName = "Zimmer";

            CustomerService customerService = new CustomerService();
            customerService.CreateCustomer(firstName, lastName);

            Customer customer = customerService.FindCustomer(firstName, lastName);
            customer.Should().NotBeNull();

            customerService.DeleteCustomer(customer.id);

            Action act = () => customerService.FindCustomer(firstName, lastName);

            act.Should().Throw<Exception>();

        }
    }
}
