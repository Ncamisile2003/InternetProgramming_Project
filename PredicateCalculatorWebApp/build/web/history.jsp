<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Calculation History</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.10.0/font/bootstrap-icons.css">
    <style>
        body {
            background-color: #f8f9fa;
        }
        .history-container {
            max-width: 1200px;
            margin: 0 auto;
            padding: 20px;
        }
        .history-card {
            border-radius: 10px;
            box-shadow: 0 4px 6px rgba(0, 0, 0, 0.1);
        }
        .table thead th {
            background-color: #0d6efd;
            color: white;
        }
        .progress {
            height: 25px;
            border-radius: 12px;
        }
        .progress-bar {
            background-color: #0d6efd;
        }
        .grade-A { background-color: rgba(40, 167, 69, 0.1); }
        .grade-B { background-color: rgba(23, 162, 184, 0.1); }
        .grade-C { background-color: rgba(255, 193, 7, 0.1); }
        .grade-D { background-color: rgba(253, 126, 20, 0.1); }
        .grade-F { background-color: rgba(220, 53, 69, 0.1); }
    </style>
</head>
<body>
    <div class="history-container">
        <!-- Header -->
        <div class="d-flex justify-content-between align-items-center mb-4">
            <h2><i class="bi bi-clock-history"></i> Calculation History</h2>
            <div>
                <a href="calculate.jsp" class="btn btn-primary me-2">
                    <i class="bi bi-plus-circle"></i> New Calculation
                </a>
                <a href="dashboard.jsp" class="btn btn-outline-secondary">
                    <i class="bi bi-house-door"></i> Dashboard
                </a>
            </div>
        </div>
        
        <!-- History Table -->
        <div class="card history-card shadow">
            <div class="card-body">
                <c:choose>
                    <c:when test="${empty calculations}">
                        <div class="alert alert-info text-center">
                            <i class="bi bi-info-circle"></i> No calculations found. 
                            <a href="calculate.jsp" class="alert-link">Create your first calculation</a>
                        </div>
                    </c:when>
                    <c:otherwise>
                        <div class="table-responsive">
                            <table class="table table-striped table-hover">
                                <thead>
                                    <tr>
                                        <th>Date</th>
                                        <th>Assignment</th>
                                        <th>Project</th>
                                        <th>Class Test</th>
                                        <th>Semester Test</th>
                                        <th>Quizzes</th>
                                        <th>Final Grade</th>
                                    </tr>
                                </thead>
                                <tbody>
                                    <c:forEach items="${calculations}" var="calc">
                                        <tr class="
                                            <c:choose>
                                                <c:when test="${calc.finalGrade >= 90}">grade-A</c:when>
                                                <c:when test="${calc.finalGrade >= 80}">grade-B</c:when>
                                                <c:when test="${calc.finalGrade >= 70}">grade-C</c:when>
                                                <c:when test="${calc.finalGrade >= 60}">grade-D</c:when>
                                                <c:otherwise>grade-F</c:otherwise>
                                            </c:choose>">
                                            <td>${calc.calculationDate}</td>
                                            <td>${calc.assignment}%</td>
                                            <td>${calc.project}%</td>
                                            <td>${calc.classTest}%</td>
                                            <td>${calc.semesterTest}%</td>
                                            <td>${calc.quiz}%</td>
                                            <td>
                                                <div class="progress">
                                                    <div class="progress-bar" 
                                                         style="width: ${calc.finalGrade}%">
                                                        ${calc.finalGrade}%
                                                    </div>
                                                </div>
                                            </td>
                                        </tr>
                                    </c:forEach>
                                </tbody>
                            </table>
                        </div>
                    </c:otherwise>
                </c:choose>
            </div>
        </div>
    </div>

    <!-- Bootstrap JS -->
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>