using KundenProjekt.Models;
using Newtonsoft.Json;
using RestSharp;
using RestSharp.Deserializers;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Net;
using System.Text;
using System.Threading.Tasks;

namespace KundenProjekt
{
    public class CustomerService
    {
        RestClient client = new RestClient("http://localhost:8080/api/v1/customer/");

        public Customer FindCustomer(string FirstName, string LastName)
        {
            RestRequest request = new RestRequest($"{FirstName}/{LastName}", Method.GET);
            IRestResponse response = client.Execute(request);

            if (response.StatusCode.Equals(HttpStatusCode.OK))
            {
                Customer customer = new JsonDeserializer().Deserialize<Customer>(response);
                return customer;
            }
            else
            {
                throw new Exception(response.Content);
            }
        }

        public void CreateCustomer(string FirstName, string LastName)
        {
            RestRequest request = new RestRequest(Method.POST);
            Customer customer = new Customer(FirstName, LastName);

            request.AddHeader("Content-Type", "application/json; charset=utf-8");
            string serializedCustomer = request.JsonSerializer.Serialize(customer);
            request.AddParameter("application/json; charset=utf-8", serializedCustomer, ParameterType.RequestBody);

            client.Execute(request);
        }

        public void DeleteCustomer(int id)
        {
            RestRequest request = new RestRequest($"{id}", Method.DELETE);
            client.Execute(request);
        }

        public List<Customer> GetAllCustomers()
        {
            RestRequest request = new RestRequest(Method.GET);
            IRestResponse response = client.Execute(request);

            if (response.StatusCode.Equals(HttpStatusCode.OK))
            {
                string jsonString = response.Content;

                var customer = JsonConvert.DeserializeObject<List<Customer>>(jsonString);
                return customer;
            }
            else
            {
                throw new Exception(response.Content);
            }

        }
    }
}
