# Spring Boot and Azure Keyvault Signing demo

## Prerequisites
- An active Azure subscription
- The Azure CLI installed on your local system (the setup script uses this)

## Running this application

The setup script will create the required items for you on Azure and will export the required details for the application into your environment. It will require some modification (setting up the variables) as mentioned below.

Note: the 

1. Edit the variables in the `setup_keyvault_local.sh` script to reflect your desired names for the keyvault. The following variables are required to be changed:

- `RESOURCE_GROUP_NAME` - The desired name of the Resource Group to create resources under.
- `KEYVAULT_NAME` - The desired name of the Keyvault to be created by the script.
- `SP_NAME` - The desired name of the Service Principal that will be usde to access the Keyvault.
- `KEY_NAME` - The desired name of the Key that will be created and used for signing and verification.

2. Run the script using `. ./setup_keyvault_local.sh`. Notice the extra dot at the beginning to export environment variables into your current shell.
3. The script will create the KeyVault, a Service Principal and a Key inside the Keyvault. The Policy for this key will be limited to the `sign` and `verify` operations so the key can not be taken out of the Keyvault.
4. Run the application using `./mvnw spring-boot:run`. You should see some output that generates a signature out of a message digest and verifies it twice (once a valid signature and once an invalid one)
5. Great success!