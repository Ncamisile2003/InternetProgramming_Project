
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Calculation Result</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
    <div class="container py-4">
        <div class="card shadow">
            <div class="card-header bg-success text-white">
                <h4>Calculation Result</h4>
            </div>
            <div class="card-body">
                <table class="table table-bordered">
                    <thead>
                        <tr class="table-primary">
                            <th>Component</th>
                            <th>Your Score</th>
                            <th>Weight</th>
                            <th>Contribution</th>
                        </tr>
                    </thead>
                    <tbody>
                        <tr>
                            <td>Assignment</td>
                            <td>${calculation.assignment}%</td>
                            <td>5%</td>
                            <td>${calculation.assignment * 0.05}%</td>
                        </tr>
                        <tr>
                            <td>Industry Project</td>
                            <td>${calculation.project}%</td>
                            <td>15%</td>
                            <td>${calculation.project * 0.15}%</td>
                        </tr>
                        <tr>
                            <td>Class Tests</td>
                            <td>${calculation.classTest}%</td>
                            <td>20%</td>
                            <td>${calculation.classTest * 0.20}%</td>
                        </tr>
                        <tr>
                            <td>Semester Tests</td>
                            <td>${calculation.semesterTest}%</td>
                            <td>45%</td>
                            <td>${calculation.semesterTest * 0.45}%</td>
                        </tr>
                        <tr>
                            <td>Quiz</td>
                            <td>${calculation.quiz}%</td>
                            <td>15%</td>
                            <td>${calculation.quiz * 0.15}%</td>
                        </tr>
                    </tbody>
                </table>
                        
                <div class="alert alert-success">
                    <h5>Final Predicate: ${calculation.finalGrade}%</h5>
                </div>
                
                <div class="text-center mt-4">
                    <a href="dashboard.jsp" class="btn btn-primary">Back to Dashboard</a>
                    <a href="calculate.jsp" class="btn btn-outline-secondary">New Calculation</a>
                </div>
            </div>
        </div>
    </div>
</body>
</html>