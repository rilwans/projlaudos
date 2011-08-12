using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Linq;
using System.Text;
using System.Windows.Forms;
using FluentNHibernate;
using NHibernate;
using FluentNHibernate.Cfg;
using FluentNHibernate.Cfg.Db;
using FluentNHibernate.Automapping;
using NHibernate.Cfg;
using NHibernate.Tool.hbm2ddl;
using NHibernate.Criterion;
using testeHibernate.Model;

namespace testeHibernate
{
    public partial class Form1 : Form
    {
        public Form1()
        {
            InitializeComponent();
            sessionFactory = BuildSessionFactory();
        }

        private static ISessionFactory BuildSessionFactory()
        {
            //This method build our session factory -
            //the most important part of our ORM application
            try
            {
                AutoPersistenceModel model = CreateMappings();

                return Fluently.Configure()
                    .Database(PostgreSQLConfiguration.PostgreSQL82
                    .ConnectionString(c => c
                        .Host("127.0.0.1")
                        .Port(5432)
                        .Database("teste")
                        .Username("postgres")
                        .Password("12345")))
                    .Mappings(m => m
                        .AutoMappings.Add(model))
                    .ExposeConfiguration(BuildSchema)
                    .BuildSessionFactory();
            }
            catch (Exception e)
            {
               Console.WriteLine(e.StackTrace);
               MessageBox.Show(e.StackTrace);
            }
            return null;

        }

        private static AutoPersistenceModel CreateMappings()
        {
            return AutoMap.Assembly(System.Reflection.Assembly.GetCallingAssembly()).Where(t => t.Namespace == "testeHibernate.Model");

        }

        private static void BuildSchema(Configuration config)
        {
            //This method will create/recreate our database
            //This method should be called only once when
            //we want to create our database
            new SchemaExport(config).Create(false, true);

        }
 

        private static ISessionFactory sessionFactory;

        private void button1_Click(object sender, EventArgs e)
        {
            try
            {
                using (ISession session = sessionFactory.OpenSession())
                {
                    using (ITransaction transaction = session.BeginTransaction())
                    {
                        TabelaHiber t = new TabelaHiber();
                        t.Id = 3;
                        t.nome = "Pardal";


                        TabelaHiber tt = new TabelaHiber();
                        tt.Id = 4;
                        tt.nome = "Teste";

                        session.Save(t);
                        session.Save(tt);

                        transaction.Commit();
                    }
                }
            }
            catch (Exception ee)
            {
                Console.WriteLine(ee.StackTrace);
                MessageBox.Show(ee.StackTrace);
            }
        }


    }
}
