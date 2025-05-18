<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Student Dashboard</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.10.0/font/bootstrap-icons.css">
    <style>
        body {
            background-color: #f8f9fa;
        }
        .dashboard-header {
            background-color: #0d6efd;
            color: white;
            padding: 2rem 0;
            margin-bottom: 2rem;
        }
        .card {
            border: none;
            border-radius: 10px;
            box-shadow: 0 4px 6px rgba(0, 0, 0, 0.1);
            transition: transform 0.3s ease;
            margin-bottom: 20px;
        }
        .card:hover {
            transform: translateY(-5px);
        }
        .card-header {
            border-radius: 10px 10px 0 0 !important;
            font-weight: 600;
        }
        .progress {
            height: 25px;
            border-radius: 12px;
        }
        .progress-bar {
            background-color: #0d6efd;
        }
        .welcome-card {
            background: linear-gradient(135deg, #0d6efd, #0b5ed7);
            color: white;
        }
    </style>
</head>
<body>
    <!-- Dashboard Header -->
    <header class="dashboard-header">
        <div class="container">
            <div class="d-flex justify-content-between align-items-center">
                <h1><i class="bi bi-calculator"></i> Predicate Calculator</h1>
                <div>
                    <span class="me-3">Welcome, <strong>${user.name}</strong></span>
                    <a href="logout" class="btn btn-outline-light">
                        <i class="bi bi-box-arrow-right"></i> Logout
                    </a>
                </div>
            </div>
        </div>
    </header>

    <div class="container">
        <!-- Quick Stats Row -->
        <div class="row mb-4">
            <div class="col-md-4">
                <div class="card welcome-card">
                    <div class="card-body text-center">
                        <h5><i class="bi bi-person"></i> Student Profile</h5>
                        <p class="mb-1"><strong>${user.username}</strong></p>
                        <p class="mb-1">${user.email}</p>
                        <a href="profile.jsp" class="btn btn-sm btn-light mt-2">
                            <i class="bi bi-pencil"></i> Edit Profile
                        </a>
                    </div>
                </div>
            </div>
            
            <div class="col-md-4">
                <div class="card bg-success text-white">
                    <div class="card-body text-center">
                        <h5><i class="bi bi-collection"></i> Total Calculations</h5>
                        <h3>${totalCalculations}</h3>
                        <a href="history.jsp" class="btn btn-sm btn-light">
                            <i class="bi bi-clock-history"></i> View History
                        </a>
                    </div>
                </div>
            </div>
            
            <div class="col-md-4">
                <div class="card bg-info text-white">
                    <div class="card-body text-center">
                        <h5><i class="bi bi-graph-up"></i> Average Grade</h5>
                        <h3>${averageGrade}%</h3>
                        <a href="calculate.jsp" class="btn btn-sm btn-light">
                            <i class="bi bi-plus-circle"></i> New Calculation
                        </a>
                    </div>
                </div>
            </div>
        </div>
    </div>

    <!-- Footer -->
    <footer class="mt-5 py-3 bg-light">
        <div class="container text-center">
            <p class="mb-0">Grade Calculator App &copy; 2025</p>
        </div>
    </footer>

    <!-- JavaScript -->
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>