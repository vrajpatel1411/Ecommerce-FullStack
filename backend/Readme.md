
# Docker Image to Kubernetes Deployment with Load Balancing on GCP

This guide provides the steps taken to build a Docker image, push it to Google Container Registry (GCR), deploy the image to a Kubernetes cluster on Google Kubernetes Engine (GKE), and expose the application via a load-balanced service.

## Prerequisites

1. **Google Cloud Platform (GCP)** account and project setup.
2. **Docker** installed and configured.
3. **Kubernetes Cluster** set up on GCP using Google Kubernetes Engine (GKE).
4. **kubectl** configured to interact with your Kubernetes cluster.
5. **Google Cloud SDK** installed and authenticated.

## Steps

### 1. Build the Docker Image

First, we create a Docker image for our application.

**Command**:
```bash
docker build -t ecommerce-backend-image:v1 .
```

- This command builds a Docker image from the current directory (`.`) and tags it as `ecommerce-backend-image:v1`.

### 2. Run the Docker Image Locally

After building the Docker image, you can run it locally to verify it.

**Command**:
```bash
docker run -p 8080:8080 ecommerce-backend-image:v1
```

- This runs the Docker container on your local machine, mapping port `8080` on the host to port `8080` inside the container.

### 3. Configure Docker for GCP Registry

To push the Docker image to GCP's Container Registry, configure Docker to authenticate with the GCP registry.

**Command**:
```bash
gcloud auth configure-docker northamerica-northeast2-docker.pkg.dev
```

- This command configures Docker to authenticate and push images to the specified registry in the GCP region `northamerica-northeast2`.

### 4. Tag the Docker Image for GCP Registry

Next, you need to tag the Docker image with the GCP Container Registry URL.

**Command**:
```bash
docker tag ecommerce-backend-image:v1 northamerica-northeast2-docker.pkg.dev/booming-edge-454201-b3/ecommerce-backend/ecommerce-backend:v1
```

- This tags the Docker image `ecommerce-backend-image:v1` with the appropriate GCP Container Registry URL (`northamerica-northeast2-docker.pkg.dev`).

### 5. Push the Docker Image to GCP Registry

Once tagged, you can push the Docker image to GCP's Container Registry.

**Command**:
```bash
docker push northamerica-northeast2-docker.pkg.dev/ecommerce-451423/ecommerce-backend/ecommerce-backend:v1
```

- This command pushes the tagged Docker image to the GCP registry for later use in Kubernetes.

### 6. Create a Kubernetes Cluster on GKE

Now, create a Kubernetes cluster on Google Kubernetes Engine (GKE).

**Command**:
```bash
gcloud container clusters create ecommerce-backend-cluster --num-nodes 2 --machine-type n1-standard-1 --region northamerica-northeast2 --disk-size 10GB
```

- This command creates a new Kubernetes cluster named `ecommerce-backend-cluster` with 2 nodes of machine type `n1-standard-1` in the `northamerica-northeast2` region. It also allocates a 10GB disk size for the cluster.

### 7. Deploy the Application Using Kubernetes

After the cluster is set up, deploy your application by applying the `deploy.yaml` configuration.

**Command**:
```bash
kubectl apply -f "deploy.yaml"
```

- This command applies the Kubernetes deployment configuration from the `deploy.yaml` file, which defines how your application should be deployed to the cluster.

### 8. Expose the Application via a Service

To expose your application, create a service by applying the `service.yaml` configuration.

**Command**:
```bash
kubectl apply -f "service.yaml"
```

- This command applies the Kubernetes service configuration from the `service.yaml` file, which exposes your application and creates a load-balanced service.

### 9. View Logs of the Deployed Application

Finally, check the logs of your application to verify that it is running correctly.

**Command**:
```bash
kubectl logs ecommerce-backend-deployment-556594b459-6mn8w
```

- This command retrieves the logs of a specific pod (`ecommerce-backend-deployment-556594b459-6mn8w`) to check if the deployment is running as expected.

## Creating Static IP address and assigning it to the Domain Name

1. Create a Static IP : gcloud compute addresses create ecomemrce-backend --global
2. Get the IP address : gcloud compute addresses describe ecomemrce-backend
## Conclusion

You have successfully:

1. Built a Docker image for your application.
2. Pushed the Docker image to Google Container Registry.
3. Created a Kubernetes cluster on GKE.
4. Deployed the application to Kubernetes.
5. Exposed the application via a load-balanced service.
6. Verified the deployment and checked the logs.

This process ensures that your application is properly deployed, scalable, and accessible through a load-balanced service on GCP.

---

