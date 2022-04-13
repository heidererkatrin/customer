using FluentAssertions;
using KundenProjekt;
using KundenProjekt.Model;
using System;
using System.Threading.Tasks;
using Xunit;

namespace KundenTest
{
    public class UnitTest1
    {
        //    [Fact]
        //    public void TestCreateKunde()
        //    {
        //        KundenService kundenService = new KundenService();
        //        kundenService.CreateCustomer("Max", "Mustermann");

        //        kundenService.GetKundenList().Should().NotBeNullOrEmpty();
        //    }

        //    [Fact]
        //    public void TestCreateCustomerDuplicate()
        //    {
        //        KundenService kundenService = new KundenService();
        //        kundenService.CreateCustomer("Max", "Mustermann");

        //        Action act = () => kundenService.CreateCustomer("Max", "Mustermann");

        //        act.Should().Throw<DuplicateNameException>();

        //    }

        //    [Fact]
        //    public void TestCreateCustomerNullName()
        //    {
        //        KundenService kundenService = new KundenService();
        //        Action act = () => kundenService.CreateCustomer("Max", null);

        //        act.Should().Throw<NameNotEmptyException>();

        //        act = () => kundenService.CreateCustomer("Max", "");

        //        act.Should().Throw<NameNotEmptyException>();

        //    }



        [Fact]
        public void TestClient()
        {
            CustomerService c = new CustomerService();
            c.GetAllCustomers();

        }
    }
}