#!/bin/bash

RESOURCE_GROUP="yourSigningDemoResourceGroupHere"
RESOURCE_GROUP_REGION="EastUS"
KEYVAULT_NAME="yourSigningDemoKeyvaultHere"
SP_NAME="http://yourSigningDemoKeyvaultSPHere"
KEY_NAME="yourkeyHere"

echo "Creating resource group..."
az group create -n $RESOURCE_GROUP -l $RESOURCE_GROUP_REGION
echo "Creating Keyvault..."
az keyvault create -n $KEYVAULT_NAME -g $RESOURCE_GROUP
echo "Creating Service Principal..."
az ad sp create-for-rbac -n $SP_NAME --sdk-auth > sp.json
echo "Setting policy..."
az keyvault set-policy -n $KEYVAULT_NAME --spn $SP_NAME --key-permissions sign verify
echo "Creating key..."
az keyvault key create -n $KEY_NAME --vault-name $KEYVAULT_NAME --kty rsa
echo "Exporting environment variables"
for keyval in $(grep -E '": [^\{]' sp.json | sed -e 's/: /=/' -e "s/\(\,\)$//" | sed 's/\"//g'); do
    echo "export $keyval"
    export $keyval
done
az keyvault key list --vault-name $KEYVAULT_NAME > key.json
for keyval in $(grep -E '": [^\{]' key.json | sed -e 's/: /=/' -e "s/\(\,\)$//" | sed 's/\"//g'); do
    echo "export $keyval"
    export $keyval
done